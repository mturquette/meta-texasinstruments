PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Baseimage inst2."
LICENSE = "LGPL"
PR = "r0"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/system/inst2/...  DSP-MM-TID-AUDIO_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/system/inst2"
CCASE_PATHCOMPONENT = "inst2"
CCASE_PATHCOMPONENTS = "4"

inherit ccasefetch

do_stage() {
	install -d ${STAGING_BINDIR}/dspbridge/system/inst2
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/system/inst2
#	install -d ${STAGING_INCDIR}/dspbridge/exports/include
#	install -m 0644 ${S}/ti/dspbridge/dsp/bridge_product/exports/include/*.h ${STAGING_INCDIR}/dspbridge/exports/include
}
