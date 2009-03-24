SECTION = "libs"
DEPENDS = "glib-2.0 tiopenmax"
DESCRIPTION = "Library for interacting OpenMAX IL."
LICENSE = "LGPL"
PR = "r0"

SRCREV = "ae5bec26f66c4a1510de1777a65c94b54e9a6385"
SRC_URI = "git://github.com/mrchapp/libgoo.git;protocol=http;branch=libgoo-5.14-rc1"
S = "${WORKDIR}/git"

EXTRA_OECONF = "--enable-ti-camera --enable-ti-clock"

inherit autotools pkgconfig

do_stage() {
	autotools_stage_all
}
