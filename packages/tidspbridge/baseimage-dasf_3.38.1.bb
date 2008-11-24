PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Baseimage dasf."
LICENSE = "LGPL"
PR = "r0"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/system/dasf/... DSP-MM-TIF-DASF_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/system/dasf"
CCASE_PATHCOMPONENT = "dasf"
CCASE_PATHCOMPONENTS = "4"

inherit ccasefetch

do_compile() {
}

do_stage() {
	install -d ${STAGING_BINDIR}/dspbridge/system/dasf
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/system/dasf
#	install -d ${STAGING_INCDIR}/dspbridge/exports/include
#	install -m 0644 ${S}/ti/dspbridge/dsp/bridge_product/exports/include/*.h ${STAGING_INCDIR}/dspbridge/exports/include
}
