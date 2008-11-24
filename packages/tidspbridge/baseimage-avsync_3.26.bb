PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU/DSP Bridge Socket Node compilation."
LICENSE = "LGPL"
PR = "r0"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/system/avsync/syncclock/... DSP-MM-TID-SYSTEM_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/system/avsync/syncclock"
CCASE_PATHCOMPONENT = "avsync"
CCASE_PATHCOMPONENTS = "4"

inherit ccasefetch

do_compile() {
}

do_stage() {
	install -d ${STAGING_BINDIR}/dspbridge/system/avsync
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/system/avsync
#	install -d ${STAGING_INCDIR}/dspbridge/exports/incluide
#	install -m 0644 ${S}/ti/dspbridge/dsp/bridge_product/exports/include/*.h ${STAGING_INCDIR}/dspbridge/exports/include
}
