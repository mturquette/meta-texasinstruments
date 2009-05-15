DESCRIPTION = "Texas Instruments WMA9 Decoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-wma9-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/audio/node/wma9/dec/... DSP-MM-TII-MM_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/audio/node/wma9/dec/"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/audio/node/wma9/dec

inherit ccasefetch tisocketnode
