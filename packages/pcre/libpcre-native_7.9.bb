DESCRIPTION = "Native Perl Compatible Regular Expression Library"
SECTION = "base"
LICENSE = "GPL"
PR = "r0"

SRC_URI = "http://www.sfr-fresh.com/fresh/unix/misc/pcre-${PV}.tar.gz"

inherit autotools native

do_unpack2(){
# Both the Packages pcre_${PV}.bb and this one(libpcre-native_${PV}.bb) can be built using the same
# source pcre-${PV}.tar.gz
# below steps moves the source into the proper directory for native libpcre build before d_configure stage

    mv ${S}/../pcre-${PV}/* ${S}/
    rm -rf ${S}/../pcre-${PV}
}

addtask unpack2 after do_unpack before do_configure
PACKAGES = "${PN}"
