SECTION = "libs"
PRIORITY = "optional"
DEPENDS = "glib-2.0 libgoo"
DESCRIPTION = "GStreamer plug-ins for OpenMAX IL based on LibGoo"
LICENSE = "LGPL"
PR = "r1"

SRC_URI = "http://afuera.cortijodelrio.net/~ddiaz/goo/gst-goo-${PV}.tar.gz"

inherit autotools pkgconfig

do_stage() {
	autotools_stage_all
}
