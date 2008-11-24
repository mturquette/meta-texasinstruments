PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Baseimage utils."
LICENSE = "LGPL"
PR = "r0"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/system/utils/...  DSP-MM-TID-SYSTEM_RLS_2.42%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/system/utils"
CCASE_PATHCOMPONENT = "utils"
CCASE_PATHCOMPONENTS = "4"

inherit ccasefetch

do_compile() {
}

do_stage() {
	install -d ${STAGING_BINDIR}/dspbridge/system/utils
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/system/utils
#	install -d ${STAGING_INCDIR}/dspbridge/exports/include
#	install -m 0644 ${S}/ti/dspbridge/dsp/bridge_product/exports/include/*.h ${STAGING_INCDIR}/dspbridge/exports/include
}
