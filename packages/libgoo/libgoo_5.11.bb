SECTION = "libs"
PRIORITY = "optional"
DEPENDS = "glib-2.0 tiopenmax"
DESCRIPTION = "Library for interacting OpenMAX IL."
LICENSE = "LGPL"
PR = "r0"

CCASE_SPEC = "%\
element /vobs/wtbu/OMAPSW_L/mmframework/... MMFRAMEWORK_REL_${PV}%\
element * /main/LATEST%\
"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_L/mmframework/libs/goo"
CCASE_PATHCOMPONENT = "goo"
CCASE_PATHCOMPONENTS = 5

EXTRA_OECONF = "--disable-ti-camera"

inherit autotools pkgconfig ccasefetch

do_stage() {
	autotools_stage_all
}
