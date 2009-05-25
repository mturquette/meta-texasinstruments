SECTION = "libs"
DEPENDS = "glib-2.0 virtual/openmax-il"
DESCRIPTION = "Library for interacting OpenMAX IL."
LICENSE = "LGPL"
PR = "r0"

SRCREV = "c67853813346dc9f9ca702084871f656f45d34e7"
SRC_URI = "git://git.omapzoom.org/repo/libgoo.git;protocol=http"
S = "${WORKDIR}/git"

EXTRA_OECONF = "--enable-ti-camera --enable-ti-clock"

inherit autotools pkgconfig

do_stage() {
	autotools_stage_all
}
