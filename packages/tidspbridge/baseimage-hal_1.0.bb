PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Baseimage hal."
LICENSE = "LGPL"
PR = "r0"

require baseimage-dasf-cspec-${PV}.inc

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/system/hal"
CCASE_PATHCOMPONENT = "hal"
CCASE_PATHCOMPONENTS = "4"

inherit ccasefetch

do_compile() {
}

do_stage() {
	chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/system/hal
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/system/hal
}
