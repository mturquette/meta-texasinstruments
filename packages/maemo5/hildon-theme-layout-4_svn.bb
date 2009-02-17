PR         = "r0"
LICENSE = "CC-BY-SA3"
DEPENDS = "matchbox-wm"
# hildon-theme-tools"
# formerly osso-esd

SRC_URI = "svn://stage.maemo.org/svn/maemo/projects/haf/trunk/;module=hildon-theme-layout-4;proto=https;rev=15856 \
	"
#           file://af-sb-init.sh.patch;patch=1;pnum=0 \
#           file://run-standalone.sh.patch;patch=1;pnum=0 \
#           file://scratchbox-launcher.sh.patch;patch=1;pnum=0 \
#	   "

S = "${WORKDIR}/${PN}"

do_stage() {
	autotools_stage_all
}

inherit autotools pkgconfig
