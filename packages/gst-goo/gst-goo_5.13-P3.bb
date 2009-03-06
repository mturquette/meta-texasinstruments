SECTION = "libs"
PRIORITY = "optional"
DEPENDS = "glib-2.0 libgoo gst-plugins-base"
DESCRIPTION = "GStreamer plug-ins for OpenMAX IL based on LibGoo"
LICENSE = "LGPL"
PR = "r0"

CCASE_SPEC = "%\
element /vobs/wtbu/OMAPSW_L/mmframework/... MMFRAMEWORK_REL_5.13%\
element * /main/LATEST%\
"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_L/mmframework/plugins/gstomxti/gst-goo"
CCASE_PATHCOMPONENT = "gst-goo"
CCASE_PATHCOMPONENTS = 6

SRC_URI += "\
	file://sinkppcapsexec.patch;patch=1 \
	file://dasf-enable.patch;patch=1 \
	file://clock.patch;patch=1 \
	"

inherit autotools pkgconfig ccasefetch

FILES_${PN} += "${libdir}/gstreamer-0.10/libgstgoo.so"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/libgstgoo.*a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"

do_stage() {
	autotools_stage_all
}
