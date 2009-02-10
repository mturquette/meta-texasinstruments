PR         = "r0"
LICENSE    = "LGPL"

DEPENDS = "gtk+ matchbox-wm dbus"
# formerly osso-esd

SRC_URI = "svn://stage.maemo.org/svn/maemo/projects/haf/trunk/;module=hildon-initscripts;proto=https;rev=17192 \
           file://af-sb-init.sh.patch;patch=1;pnum=0 \
           file://run-standalone.sh.patch;patch=1;pnum=0 \
           file://scratchbox-launcher.sh.patch;patch=1;pnum=0 \
	   "

S = "${WORKDIR}/${PN}"

inherit autotools pkgconfig
