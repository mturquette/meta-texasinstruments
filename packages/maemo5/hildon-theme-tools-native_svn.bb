PR	= "r0"
LICENSE	= "GPL"
DEPENDS	= "matchbox-wm"
#DEPENDS = "hildon-theme-layout-4"
# formerly osso-esd

SRC_URI = "svn://stage.maemo.org/svn/maemo/projects/haf/trunk/;module=hildon-theme-tools;proto=https;rev=15856 \
	"
#           file://af-sb-init.sh.patch;patch=1;pnum=0 \
#           file://run-standalone.sh.patch;patch=1;pnum=0 \
#           file://scratchbox-launcher.sh.patch;patch=1;pnum=0 \
#	   "

S = "${WORKDIR}/hildon-theme-tools"

do_stage() {
	autotools_stage_all
}

inherit autotools pkgconfig native
