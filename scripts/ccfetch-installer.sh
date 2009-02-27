#!/bin/bash
# 
# Description:
#  An installer for the ccfetch scripts, for fetching the src code for 
#  components which still use clearcase.
# 
# TODO: 
#  1) figure out what location we are at and pick sane defaults accordingly..
#  2) sanity checking.. make sure CCF_LOCALBIN and CCF_REMOTEBIN are in the $PATH on the
#     local and remote machines
#  3) ensure CCF_REMOTEHOME/.ssh exists
#  4) ensure cleartool is found (test -x ..)
#  5) allow alternate ssh keys..


VERSION="2.1.3"
echo "Installing v$VERSION ccfetch scripts"

################################################################################
### some helper functions used later:

function move_to_remote() {
  file="$1"
  scp $file $CCF_REMOTEUSER@$CCF_REMOTEHOST:$CCF_REMOTEBIN/`basename $file` &&
    rm $file
}

function prompt() {
  var=$1
  what=$2
  rc="0"
  eval 'echo -n "  Enter $what: [$'$var'] "'
  read result
  if [ -z $result ]; then
    result=`eval 'echo -n "$'$var'"'`
    rc="1"
  fi
  eval $var=\$result
  return $rc
}

function prompt_yes_no() {
  while `true`; do
    echo -n "  Y/N> "
    read -n 1 result
    echo ""
    case $result in
      Y|y)
        return 0
        ;;
      N|n)
        return 1
        ;;
      *)
        echo "invalid input: $result"
    esac
  done
}

################################################################################
### Setup some sane defaults:

CCF_LOCALBIN="$HOME/bin"
CCF_REMOTEHOST="dirac.dal.design.ti.com"
CCF_REMOTEUSER="$USER"
CCF_REMOTEVIEW="omapsw_${CCF_REMOTEUSER}_poky_temp"
CCF_REMOTEHOME="/home/$CCF_REMOTEUSER"
CCF_REMOTEBIN="$CCF_REMOTEHOME/bin"

### but if we have values from the previous install, take them instead:
if [ -r $CCF_LOCALBIN/cfetchcc.conf ]; then
  source $CCF_LOCALBIN/cfetchcc.conf
fi

################################################################################
### Prompt for remote host, username, etc:
echo ""
echo "But first, I have a couple questions:"
while `true`; do
  prompt CCF_LOCALBIN   "local bin directory (must be in \$PATH)"
  prompt CCF_REMOTEHOST "remote hostname"
  prompt CCF_REMOTEUSER "remote username"                &&
    CCF_REMOTEVIEW="omapsw_${CCF_REMOTEUSER}_poky_temp"  &&
    CCF_REMOTEHOME="/home/$CCF_REMOTEUSER"               &&
    CCF_REMOTEBIN="$CCF_REMOTEHOME/bin"
  prompt CCF_REMOTEVIEW "remote viewname"
  prompt CCF_REMOTEHOME "remote home directory path"     &&
    CCF_REMOTEBIN="$CCF_REMOTEHOME/bin"
  prompt CCF_REMOTEBIN  "remote bin directory (must be in \$PATH on $CCF_REMOTEHOST)"
  echo "Ok, I've got:"
  echo "  local bin directory   : $CCF_LOCALBIN"
  echo "  remote hostname       : $CCF_REMOTEHOST"
  echo "  remote username       : $CCF_REMOTEUSER"
  echo "  remote viewname       : $CCF_REMOTEVIEW"
  echo "  remote home directory : $CCF_REMOTEHOME"
  echo "  remote bin directory  : $CCF_REMOTEBIN"
  echo "Is this correct?"
  prompt_yes_no &&
    break
done



################################################################################
### Setup SSH public/private keys on local and remote host

function check_id_file() {
  idfile=$1
  CCF_IDFILE=""
  if [ -r $HOME/.ssh/$idfile.pub ] &&
     [ -r $HOME/.ssh/$idfile ]; then
    echo "Found identity $idfile.. should I use this?  (Only say 'Y'es if it has an empty passphrase)"
    prompt_yes_no &&
      CCF_IDFILE=$idfile
  fi
}

### 1) figure out if we already have some sort of public/private key pair
###    already created:
while `true`; do
  [ -n "$CCF_IDFILE" ] && [ "$CCF_IDFILE" != "id_rsa" ] && [ "$CCF_IDFILE" != "id_dsa" ] && check_id_file $CCF_IDFILE
  [ -z "$CCF_IDFILE" ] && check_id_file "id_dsa"
  [ -z "$CCF_IDFILE" ] && check_id_file "id_rsa"
  if [ -r $HOME/.ssh/$CCF_IDFILE.pub ] &&
     [ -r $HOME/.ssh/$CCF_IDFILE ]; then
    break
  fi
  type="rsa"
  CCF_IDFILE="id_$type"
  prompt CCF_IDFILE "Enter identity name"
  if [ -r $HOME/.ssh/$CCF_IDFILE.pub ] &&
     [ -r $HOME/.ssh/$CCF_IDFILE ]; then
    echo "Found exiting identity $CCF_IDFILE.. should I use this?  (Only say 'Y'es if it has an empty passphrase)"
    prompt_yes_no &&
      break
  else
    break
  fi
  CCF_IDFILE=""
done

### 2) if we did not already find a suitable public/private key pair, then
###    lets create one now:
if [ -r $HOME/.ssh/$CCF_IDFILE.pub ] &&
   [ -r $HOME/.ssh/$CCF_IDFILE ]; then
  echo "Using existing identity $CCF_IDFILE"
else
  echo "Now I will create a $type key.  For the ccfetch scripts to run properly from"
  echo "bitbake, an empty passphrase must be used.  So when prompted for a passphrase"
  echo "press ENTER (twice):"
  echo ""
  ssh-keygen -t $type -f $HOME/.ssh/$CCF_IDFILE
fi

### 3) append public key to remote host's ~/.ssh/authorized_keys
CCF_REMOTEEXEC="ssh -i $HOME/.ssh/$CCF_IDFILE $CCF_REMOTEUSER@$CCF_REMOTEHOST"
echo ""
echo "Now I will copy your public key to $CCF_REMOTEHOST.  Enter password for $CCF_REMOTEUSER@$CCF_REMOTEHOST"
echo "if prompted:"
$CCF_REMOTEEXEC mkdir -p $CCF_REMOTEHOME/.ssh
$CCF_REMOTEEXEC chmod 700 $CCF_REMOTEHOME/.ssh
### XXX check if key is already in authorized_keys before appending it again:
cat $HOME/.ssh/$CCF_IDFILE.pub | $CCF_REMOTEEXEC tee -a $CCF_REMOTEHOME/.ssh/authorized_keys > /dev/null


################################################################################
### Create local and remote directories, if needed:
mkdir -p $CCF_LOCALBIN
$CCF_REMOTEEXEC mkdir -p $CCF_REMOTEBIN
$CCF_REMOTEEXEC mkdir -p $CCF_REMOTEHOME/specs

################################################################################
### Find remote cleartool executable:
if [ -n $CCF_REMOTECLEARTOOL ]; then
  $CCF_REMOTEEXEC test -x $CCF_REMOTECLEARTOOL
  if [ $? != 0 ]; then
    CCF_REMOTECLEARTOOL=""
  fi
fi

if [ -z $CCF_REMOTECLEARTOOL ]; then
  ### see if it's in the users path:
  CCF_REMOTECLEARTOOL=`$CCF_REMOTEEXEC which cleartool`
  if [ $? != 0 ]; then
    CCF_REMOTECLEARTOOL=""
    echo -n "  Hmm, I can't find your clearcase installation."
    prompt CCF_REMOTECLEARTOOL "path to cleartool"
  fi
fi


echo ""
echo ""
echo "And now finally, I have some files to install:"

################################################################################
### Install cfetchcc-get.sh
echo " * installing cfetchcc-get.sh"
cat > $CCF_LOCALBIN/cfetchcc-get.sh <<EOF
#!/bin/bash
# \$@ = elements to fetch
#
# e.g.:
#  cat myconfigspec.cs | \\
#    cfetchcc-get.sh \\
#      /vobs/wtbu/OMAPSW_L/mmframework/libs/goo \\
#      /vobs/wtbu/OMAPSW_L/mmframework/libs/Makefile
#
# This will construct a config spec based on the one given from standard input
# (either cat'ed or typed in), will fetch elements
# /vobs/wtbu/OMAPSW_L/mmframework/libs/{goo,Makefile}, and will create a
# gzipped tarball of that.

# Configuration options
ROOTDIR=\$(readlink -f \`dirname \$0\`)
source \${ROOTDIR}/cfetchcc.conf


if [ -z "\${1}" ]; then
        echo "Need at least 1 argument:"
        echo " cfetchcc-get.sh <element to fetch> [element to fetch] [elements to fetch...]"
        echo "E.g.:"
        echo "  cat myconfigspec.cs | \\\\"
        echo "    cfetchcc-get.sh \\\\"
        echo "      /vobs/wtbu/OMAPSW_L/mmframework/libs/goo \\\\"
        echo "      /vobs/wtbu/OMAPSW_L/mmframework/libs/Makefile \\\\"
        exit 1
fi

echo >&2 "\`basename \$0\` v$VERSION"
\${CCF_REMOTEEXEC} \${CCF_REMOTEBIN}/sfetchcc-get.sh "\$@"
EOF
chmod +x $CCF_LOCALBIN/cfetchcc-get.sh


################################################################################
### Install cfetchdirac.sh
echo " * installing cfetchdirac.sh"
cat > $CCF_LOCALBIN/cfetchdirac.sh <<EOF
#!/bin/bash
# \$@ = elements to fetch
#
# e.g.:
#  cfetchdirac.sh \\
#    /vobs/wtbu/OMAPSW_L/mmframework/libs/goo \\
#    /vobs/wtbu/OMAPSW_L/mmframework/libs/Makefile
#
# This will create a gzipped tarball of the elements
# /vobs/wtbu/OMAPSW_L/mmframework/libs/{goo,Makefile}.

# Configuration options
ROOTDIR=\$(readlink -f \`dirname \$0\`)
source \${ROOTDIR}/cfetchcc.conf


if [ -z "\${1}" ]; then
        echo "Need at least 1 argument:"
        echo " cfetchdirac.sh <element to fetch> [element to fetch] [elements to fetch...]"
        echo "E.g.:"
        echo "  cfetchdirac.sh \\\\"
        echo "    /vobs/wtbu/OMAPSW_L/mmframework/libs/goo \\\\"
        echo "    /vobs/wtbu/OMAPSW_L/mmframework/libs/Makefile \\\\"
        exit 1
fi

echo >&2 "\`basename \$0\` v$VERSION"
\${CCF_REMOTEEXEC} \${CCF_REMOTEBIN}/sfetchcc-mktar.sh "\$@"
EOF
chmod +x $CCF_LOCALBIN/cfetchdirac.sh


################################################################################
### Generate cfetchcc.conf
echo " * generating cfetchcc.conf"
cat > $CCF_LOCALBIN/cfetchcc.conf <<EOF
# Server/client configuration options for fetchcc

# CCF_REMOTEVIEW
# A ClearCase view that will be used by the scripts.
# It's not the development view, but rather a clean
# view. Its config spec will be rewritten many times
# by the class.
CCF_REMOTEVIEW="$CCF_REMOTEVIEW"

# CCF_CONFIGSPEC
# The file that will temporarily hold the config
# spec used by the recipe. This config spec will be
# used with aforementioned view. It is reused over
# the time.
CCF_CONFIGSPEC="$CCF_REMOTEHOME/specs/forpoky.cs"

# CCF_TIMEOUT
# This time-out value indicates how long the script
# will wait before it engages in fetching the code
# from ClearCase. If multiple threads are running,
# it's possible that some others will hog the line
# for a good time.
CCF_TIMEOUT="900"

CCF_REMOTEHOST="$CCF_REMOTEHOST"
CCF_REMOTEUSER="$CCF_REMOTEUSER"
CCF_REMOTEHOME="$CCF_REMOTEHOME"
CCF_REMOTEBIN="$CCF_REMOTEBIN"
CCF_REMOTECLEARTOOL="$CCF_REMOTECLEARTOOL"
CCF_REMOTEEXEC="$CCF_REMOTEEXEC"

# note: these arent't actually used by the ccfetch scripts,
# but they are here so the next time the user runs the
# installer script, it remembers their previous choices:
CCF_LOCALBIN="$CCF_LOCALBIN"
CCF_IDFILE="$CCF_IDFILE"

EOF


################################################################################
### Install sfetchcc-get.sh remotely
echo " * installing sfetchcc-get.sh remotely"
cat > $CCF_LOCALBIN/sfetchcc-get.sh <<EOF
#!/bin/bash
# \$@ = elements to fetch
#
# e.g.:
#  cat myconfigspec.cs | \\
#    sfetchcc-get.sh \\
#      /vobs/wtbu/OMAPSW_L/mmframework/libs/goo \\
#      /vobs/wtbu/OMAPSW_L/mmframework/libs/Makefile
#
# Notes:
#  CCF_CONFIGSPEC is a file that will store the
#    configuration specification (config spec)
#    as the recipe indicates.
#  CCF_LOCKFILE is a lock that ensures the config
#    spec file is being used only by one
#    instance.
#  CCF_TIMEOUT is a time limit to engage the lock
#    and fetch files from the ClearCase view.
#  CCF_REMOTEVIEW is the name of the ClearCase view used
#    to set a config spec and fetch files.

# Configuration options
ROOTDIR=\$(readlink -f \`dirname \$0\`)
source \${ROOTDIR}/sfetchcc.conf
CCF_LOCKFILE=\${CCF_CONFIGSPEC}.lock

function usage() {
        echo "-c, -L, -t, -v"
}

TEMP=\`getopt -o hC:c:L:t:v: -l configspec:,content:,help,lockfile:,timeout:,view: -- "\$@"\`
if [ \$? != 0 ]; then echo "Error parsing options. Exiting." >&2; exit 1; fi

eval set -- "\$TEMP"

TAROPTS=
while true; do
        case "\${1}" in
                -C|--configspec)
                        CCF_CONFIGSPEC="\${2}"
                        shift 2 ;;
                -c|--content)
                        TAROPTS="-c \${2}"
                        shift 2 ;;
                -h|--help)
                        usage; exit 0 ;;
                -L|--lockfile)
                        CCF_LOCKFILE="\${2}"
                        shift 2 ;;
                -t|--timeout)
                        CCF_TIMEOUT="\${2}"
                        shift 2 ;;
                -v|--view)
                        CCF_REMOTEVIEW="\${2}"
                        shift 2 ;;
                --)
                        shift; break ;;
                *) echo "Internal error"; exit 1 ;;
        esac
done

echo >&2 "\`basename \$0\` v$VERSION"

function clean_lock() {
        rm -f \${CCF_LOCKFILE}
}



# Lock here
trap "clean_lock; exit" INT TERM EXIT
ctr=0;
while [ 1 ]; do
        while [ 1 ]; do
                ln -s \$\$ \${CCF_LOCKFILE} 2>/dev/null
                RES=\$?
                if [ \${RES} -eq 0 ]; then break; fi

                sleep 1; ctr=\$((\$ctr+1));
                if [ \$ctr -ge \${CCF_TIMEOUT} ]; then
                        echo "ERROR: Timeout waiting for ClearCase view lock." >&2;
                        exit 1;
                fi
        done

        # make sure the lock is really ours
        sleep 1
        LOCKOWNER=\$(readlink \${CCF_LOCKFILE})
        if [ "\${LOCKOWNER}" = "\$\$" ]; then break;
        else echo "WARNING: Lock is there, not mine." >&2;
        fi
done

# Work here
cat > \${CCF_CONFIGSPEC}
CMD="\${CCF_REMOTEBIN}/sfetchcc-mktar.sh \${TAROPTS} \$@"
\${CCF_REMOTECLEARTOOL} setcs -tag \${CCF_REMOTEVIEW} \${CCF_CONFIGSPEC}
\${CCF_REMOTECLEARTOOL} setview -exec "\${CMD}" \${CCF_REMOTEVIEW}

# Unlock here
clean_lock
trap - INT TERM EXIT

EOF
chmod +x $CCF_LOCALBIN/sfetchcc-get.sh
move_to_remote $CCF_LOCALBIN/sfetchcc-get.sh


################################################################################
### Install sfetchcc-mktar.sh remotely
echo " * installing sfetchcc-mktar.sh remotely"
cat > $CCF_LOCALBIN/sfetchcc-mktar.sh <<EOF
#!/bin/bash

# \$@ = elements to fetch
# 
# e.g.:
#  sfetchcc-mktar.sh /vobs/wtbu/OMAPSW_L/mmframework/libs/goo /vobs/wtbu/OMAPSW_L/mmframework/libs/Makefile

usage()
{
        echo "sfetchcc-mktar.sh [[ -c|--content=file files-to-add ] | -h ]"
        echo "-c --content=file         Store files listed in file"
        echo "-h --help                 Print this help"
        echo ""
        echo "Files will be tarred up, gzipped, and printed to standard output."
}

version()
{
        echo >&2 "\`basename \$0\` v$VERSION"
}

TEMP=\`getopt -o hc:V -l content:,help,version -- "\$@"\`
if [ \$? != 0 ]; then echo "Error parsing options. Exiting." >&2; exit 1; fi

eval set -- "\$TEMP"

TAROPTS=
while true; do
        case "\${1}" in
                -c|--content)
                        TAROPTS="\${TAROPTS} -T \"\${2}\""
                        shift 2 ;;
                -h|--help)
                        usage; exit 0 ;;
                -V|--version)
                        version; exit 0 ;;
                --)
                        shift; break ;;
                *) echo "Internal error"; exit 1 ;;
        esac
done

version

tar zcf - \${TAROPTS} \$@
EOF
chmod +x $CCF_LOCALBIN/sfetchcc-mktar.sh
move_to_remote $CCF_LOCALBIN/sfetchcc-mktar.sh


################################################################################
### Generate sfetchcc.conf
echo " * generating sfetchcc.conf remotely"
cp $CCF_LOCALBIN/cfetchcc.conf $CCF_LOCALBIN/sfetchcc.conf
move_to_remote $CCF_LOCALBIN/sfetchcc.conf

################################################################################
echo "Done!"

