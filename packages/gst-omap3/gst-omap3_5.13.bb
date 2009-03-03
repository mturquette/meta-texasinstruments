SECTION = "libs"
PRIORITY = "optional"
DEPENDS = "glib-2.0 gst-plugins-base mm-isp"
DESCRIPTION = "GStreamer plug-ins for OMAP3"
LICENSE = "LGPL"
PR = "r0"

CCASE_SPEC = "%\
element /vobs/wtbu/OMAPSW_L/mmframework/... MMFRAMEWORK_REL_${PV}%\
element * /main/LATEST%\
"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_L/mmframework/plugins/gstcommon/gst-omap3"
CCASE_PATHCOMPONENT = "gst-omap3"
CCASE_PATHCOMPONENTS = 6

inherit autotools pkgconfig ccasefetch

FILES_${PN} += "${libdir}/gstreamer-0.10/libgst*.so"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/libgst*.*a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"

do_stage() {
	autotools_stage_all
}
