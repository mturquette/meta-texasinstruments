DESCRIPTION = "Texas Instruments G711 Encoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-g711enc-codec \
	    tisocketnode-nmu-plc-vad \
"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/speech/node/g711/enc/... DSP-MM-TII-MM_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/speech/node/g711/enc"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/speech/node/g711/enc

#set to release or debug
RELEASE = "release"

inherit ccasefetch tisocketnode
