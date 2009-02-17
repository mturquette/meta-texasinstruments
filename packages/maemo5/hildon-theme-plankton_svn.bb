PR         = "r0"
LICENSE = "CC-BY-SA3"
DEPENDS = "matchbox-wm hildon-theme-layout-4"
# formerly osso-esd

#EXTRA_OECONF = "--datadir=${STAGING_DATADIR}"

do_configure () {
	oe_runconf --datadir=${STAGING_DATADIR}
}

SRC_URI = "svn://stage.maemo.org/svn/maemo/projects/haf/trunk/;module=hildon-theme-plankton;proto=https;rev=15856 \
	"
#           file://af-sb-init.sh.patch;patch=1;pnum=0 \
#           file://run-standalone.sh.patch;patch=1;pnum=0 \
#           file://scratchbox-launcher.sh.patch;patch=1;pnum=0 \
#	   "

S = "${WORKDIR}/${PN}"

inherit autotools pkgconfig
