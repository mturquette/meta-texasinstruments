SECTION = "libs"
DEPENDS = "glib-2.0 tiopenmax"
DESCRIPTION = "Library for interacting OpenMAX IL."
LICENSE = "LGPL"
PR = "r0"

SRCREV = "1a22446184c583de975efde4423bfab3499d711d"
SRC_URI = "git://github.com/mrchapp/libgoo.git;protocol=http;branch=master"
S = "${WORKDIR}/git"

EXTRA_OECONF = "--enable-ti-camera --enable-ti-clock"

inherit autotools pkgconfig

do_stage() {
	autotools_stage_all
}
