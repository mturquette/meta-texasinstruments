DESCRIPTION = "Texas Instruments algo for IPP."
PR = "r0"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/algo/... DSP-MM-TID-IMVID_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/algo"
CCASE_PATHCOMPONENT = "algo"
CCASE_PATHCOMPONENTS = "3"

inherit ccasefetch

do_stage() {
	install -d ${STAGING_BINDIR}/dspbridge/algo
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/algo
}
