SECTION = "libs"
PRIORITY = "optional"
DEPENDS = "glib-2.0 tiopenmax"
DESCRIPTION = "Library for interacting OpenMAX IL."
LICENSE = "LGPL"
PR = "r1"

SRC_URI = "http://afuera.cortijodelrio.net/~ddiaz/goo/libgoo-${PV}.tar.gz"
EXTRA_OECONF = "--disable-ti-camera"

inherit autotools pkgconfig

do_stage() {
	autotools_stage_all
}
