DESCRIPTION = "Texas Instruments DASF Control Task Node Node."
PR = "r0"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/system/dasf/... DSP-MM-TIF-DASF_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/system/dasf"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/system/dasf/ControlTaskNode

inherit ccasefetch tisocketnode
