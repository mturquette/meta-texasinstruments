SECTION = "console/network"
DESCRIPTION = "Iperf is a tool to measure maximum TCP bandwidth, allowing the tuning of various parameters and UDP characteristics"
HOMEPAGE = "http://iperf.sourceforge.net"
LICENSE = "BSD"
PR = "r0"

SRC_URI = "http://downloads.sourceforge.net/iperf/iperf-2.0.4.tar.gz"
#SRC_URI = "http://dast.nlanr.net/Projects/Iperf2.0/iperf-${PV}.tar.gz"
#	file://socketaddr-h-errno.diff;patch=1"

inherit autotools

#EXTRA_OECONF += "

### original recipe below ###

#S="${WORKDIR}/iperf-${PV}"

# --disable-threads is needed on epia/x86 with uclibc
#do_configure() {
#	gnu-configize
#	oe_runconf --exec-prefix=${STAGING_DIR_HOST}${layout_exec_prefix}
#	#oe_runconf --exec-prefix=${STAGING_DIR_HOST}${layout_exec_prefix} \
#	#--disable-threads
#}

#do_compile() {
#	cd ${WORKDIR}/iperf-${PV}
#	oe_runmake
#}

#do_install() {
#	cd ${WORKDIR}/iperf-${PV}/src
#	oe_runmake DESTDIR=${D} install
#}

