PRIORITY = "optional"
DESCRIPTION = "Texas Instruments DSP Bridge tool DLLcreate"
LICENSE = "Texas Instruments"
PR = "r0"

CCASE_SPEC = "%\
              element /vobs/SDS/Source/Dload/dload/... .../dyn-load_rel_1.x/LATEST%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/SDS/Source/Dload/dload/src"
CCASE_PATHCOMPONENT = "src"
CCASE_PATHCOMPONENTS = "5"

SRC_URI = "file://DLLcreate.patch;patch=1"

inherit ccasefetch

do_compile() {
	cd DLLcreate
        chmod -R +w *
	mkdir -p linux/release
	oe_runmake -f makefile.lin	
}

do_stage() {
	cd ${STAGING_BINDIR}
	install -d ${STAGING_BINDIR}/DLLcreate
	install -m 0755 ${S}/DLLcreate/linux/release/DLLcreate ${STAGING_BINDIR}/DLLcreate
}
