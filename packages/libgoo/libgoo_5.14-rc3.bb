SECTION = "libs"
DEPENDS = "glib-2.0 tiopenmax"
DESCRIPTION = "Library for interacting OpenMAX IL."
LICENSE = "LGPL"
PR = "r0"

SRCREV = "d3c133ca3011429cd896ec0af87b7be336227a04"
SRC_URI = "git://github.com/mrchapp/libgoo.git;protocol=http;branch=libgoo-5.14-rc3"
S = "${WORKDIR}/git"

EXTRA_OECONF = "--enable-ti-camera --enable-ti-clock"

inherit autotools pkgconfig

do_stage() {
	autotools_stage_all
}
