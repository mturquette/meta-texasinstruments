SECTION = "multimedia"
DESCRIPTION = "Common library for accessing Microsoft Media Server (MMS) media streaming protocol"
LICENSE = "LGPL-2"
DEPENDS = "glib-2.0"
PR = "r0"

inherit autotools pkgconfig

SRC_URI = "http://downloads.sourceforge.net/libmms/libmms-0.3.tar.gz"

do_unpack2() {
	sed -e "s:<malloc.h>:<stdlib.h>:g" -i src/uri.c
}

do_stage() {
	autotools_stage_all
}

addtask unpack2 after do_unpack before do_patch
