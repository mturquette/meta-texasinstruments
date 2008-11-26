PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Baseimage hal."
LICENSE = "LGPL"
PR = "r0"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/system/hal/... DSP-MM-TIF-DASF_RLS_${PV}%\
	      element * /main/LATEST%"

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
