DESCRIPTION = "Texas Instruments G726 Decoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-g726dec-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/speech/node/g726/dec/... DSP-MM-TII-MM_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/speech/node/g726/dec"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/speech/node/g726/dec

#set to release or debug
RELEASE = "release"

inherit ccasefetch tisocketnode
