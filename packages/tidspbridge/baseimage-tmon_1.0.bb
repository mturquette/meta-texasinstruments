PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU/DSP Bridge Socket Node compilation."
LICENSE = "LGPL"
PR = "r0"

require baseimage-dasf-cspec-${PV}.inc

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/system/tmon"
CCASE_PATHCOMPONENT = "tmon"
CCASE_PATHCOMPONENTS = "4"

inherit ccasefetch

do_compile() {
}

do_stage() {
        chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/system/tmon
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/system/tmon
}
