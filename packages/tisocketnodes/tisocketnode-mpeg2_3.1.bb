DESCRIPTION = "Texas Instruments MPEG2 Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-mpeg2-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/video/node/mpeg2/... DSP-MM-TII-MM_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/video/node/mpeg2"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/video/node/mpeg2/dec

inherit ccasefetch tisocketnode
