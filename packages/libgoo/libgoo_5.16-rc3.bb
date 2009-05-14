SECTION = "libs"
DEPENDS = "glib-2.0 tiopenmax"
DESCRIPTION = "Library for interacting OpenMAX IL."
LICENSE = "LGPL"
PR = "r0"

SRCREV = "6527999590a8ddfd5b785f5ca7a8dfdcac30db02"
SRC_URI = "git://github.com/mrchapp/libgoo.git;protocol=http;branch=libgoo-5.16-rc"
S = "${WORKDIR}/git"

EXTRA_OECONF = "--enable-ti-camera --enable-ti-clock"

inherit autotools pkgconfig

do_stage() {
	autotools_stage_all
}
