PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Baseimage utils."
LICENSE = "LGPL"
PR = "r0"

require baseimage-system-cspec-${PV}.inc

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/system/utils"
CCASE_PATHCOMPONENT = "utils"
CCASE_PATHCOMPONENTS = "4"

inherit ccasefetch

do_compile() {
}

do_stage() {
        chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/system/utils
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/system/utils
}
