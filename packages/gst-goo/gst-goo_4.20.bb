SECTION = "libs"
PRIORITY = "optional"
DEPENDS = "glib-2.0 libgoo gstreamer"
DESCRIPTION = "GStreamer plug-ins for OpenMAX IL based on LibGoo"
LICENSE = "LGPL"
PR = "r1"

#SRC_URI = "http://afuera.cortijodelrio.net/~ddiaz/goo/gst-goo-${PV}.tar.gz"
CCASE_SPEC = "%\
element /vobs/wtbu/OMAPSW_L/mmframework/... MMFRAMEWORK_REL_${PV}%\
element * /main/LATEST%\
"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_L/mmframework/plugins/gstomxti/gst-goo"
CCASE_PATHCOMPONENT = "gst-goo"
CCASE_PATHCOMPONENTS = 6

inherit autotools pkgconfig ccasefetch

FILES_${PN} += "${libdir}/gstreamer-0.10/libgstgoo.so"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/libgstgoo.*a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"

do_stage() {
	autotools_stage_all
}
