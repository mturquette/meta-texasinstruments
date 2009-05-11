DESCRIPTION = "Texas Instruments G729 Encoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-g729enc-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/speech/node/g729/enc/... DSP-MM-TII-MM_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/speech/node/g729/enc"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/speech/node/g729/enc

#set to release or debug
RELEASE = "release"

inherit ccasefetch tisocketnode
