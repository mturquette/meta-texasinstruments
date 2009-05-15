DESCRIPTION = "Texas Instruments MPEG4 AAC Decoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-mpeg4aacdec-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/audio/node/mpeg4aac/dec/... DSP-MM-TII-MM_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/audio/node/mpeg4aac/dec"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/audio/node/mpeg4aac/dec

inherit ccasefetch tisocketnode
