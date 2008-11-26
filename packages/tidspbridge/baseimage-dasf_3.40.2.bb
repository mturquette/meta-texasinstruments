PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Baseimage dasf."
LICENSE = "LGPL"
PR = "r0"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/system/dasf/... DSP-MM-TIF-DASF_RLS_${PV}%\
	      element /vobs/wtbu/OMAPSW_DSP/system/dasf/... DSP-MM-TIF-DASF_RLS_3.40.1%\
	      element * /main/LATEST%"

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
