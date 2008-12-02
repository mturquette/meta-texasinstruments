SECTION = "multimedia"
DEPENDS = "unzip-native"
DESCRIPTION = "Wrapper library for 3GPP Adaptive Multi-Rate Floating-point Speech Codec"
LICENSE = "LGPL-2 as-is"
PR = "r0"

inherit autotools

SPEC_VER="26104-700"

SRC_URI = "http://ftp.penguin.cz/pub/users/utx/amr/${PN}-${PV}.tar.bz2 \
	http://www.3gpp.org/ftp/Specs/archive/26_series/26.104/${SPEC_VER}.zip \
	file://crossnoshell2.patch;patch=1 \
	"

do_unpack2() {
	cd "${S}"
	cp -p ${DL_DIR}/${SPEC_VER}.zip .
}

do_afterconf() {
	cd "${S}"
	chmod +x prepare_sources.sh
}

do_stage() {
	autotools_stage_all
}

addtask unpack2 after do_unpack before do_patch
addtask afterconf after do_configure before do_compile
