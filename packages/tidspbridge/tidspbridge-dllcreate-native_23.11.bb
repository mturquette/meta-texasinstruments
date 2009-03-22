PRIORITY = "optional"
DESCRIPTION = "Texas Instruments DSP Bridge tool DLLcreate"
LICENSE = "Texas Instruments"
PR = "r1"

CCASE_SPEC = "%\
              element /vobs/SDS/Source/Dload/dload/... .../dyn-load_rel_1.x/LATEST%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/SDS/Source/Dload/dload/src"
CCASE_PATHCOMPONENT = "src"
CCASE_PATHCOMPONENTS = "5"

SRC_URI = "file://DLLcreate.patch;patch=1"

inherit ccasefetch native

# DLLcreate is not portable to 64bit environment, so force it to
# be built as a 32bit executable, even if we are using an x86_64
# machine to build:
export GCC = "gcc -m32"

do_compile() {
	# this file conflicts with headers from newer GCC compilers (ie. v4.3.x), so we
	# must remove it (even if gcc-4.2 is also installed):
	rm -f shared/stdint.h

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
