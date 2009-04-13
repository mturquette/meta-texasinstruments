PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Baseimage make system."
LICENSE = "LGPL"
PR = "r0"

require baseimage-system-cspec-${PV}.inc

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/make"
CCASE_PATHCOMPONENT = "make"
CCASE_PATHCOMPONENTS = "3"

inherit ccasefetch

do_stage() {
        chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/make
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/make
}
