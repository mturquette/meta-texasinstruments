SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments codegen tools"
LICENSE = "Texas Instruments"
PR = "r1"

inherit dfetch native

DIRAC_PATHFETCH = "/data/omapts/linux/dsp-tc/cgt6x-${PV}"
DIRAC_PATHCOMPONENT = "cgt6x-${PV}"
DIRAC_PATHCOMPONENTS = 4

do_stage() {
	chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/tools/cgt6x-${PV}
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/tools
}
