DESCRIPTION = "Texas Instruments H264 Decoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-h264dec-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/video/node/h264/dec/... DSP-MM-TII-MM_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/video/node/h264/dec"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/video/node/h264/dec

inherit ccasefetch tisocketnode
