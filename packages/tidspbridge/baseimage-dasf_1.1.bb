PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Baseimage dasf."
LICENSE = "LGPL"
PR = "r0"

require baseimage-dasf-cspec-${PV}.inc

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/system/dasf"
CCASE_PATHCOMPONENT = "dasf"
CCASE_PATHCOMPONENTS = "4"

inherit ccasefetch

do_compile() {
}

do_stage() {
        chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/system/dasf
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/system/dasf
}
