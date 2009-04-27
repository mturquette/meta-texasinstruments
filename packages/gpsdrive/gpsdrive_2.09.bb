DESCRIPTION = "GPS Drive MAP application Bitbake"
LICENSE = "GPL"
SECTION = "base"
PR = "r0"

DEPENDS += "libpcre-native pcre"

SRC_URI = "http://www.gpsdrive.de/packages/gpsdrive-${PV}.tar.gz"
inherit autotools pkgconfig

EXTRA_OECONF = " \
 	     --disable-garmin \
         --target=arm-none-linux-gnueabi \
         --host=i686-pc-linux-gnu "

LDFLAGS += "-L${STAGING_LIBDIR}"
DFLAGS = "-L/usr/lib/"

do_install_append(){
#remove gpsd from bin dir, we have it installed separately in sbin dir
	rm ${D}${bindir}/gpsd
}
