DESCRIPTION = "Texas Instruments WB-AMR Encoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-wbamrenc-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/speech/node/wbamr/enc/... DSP-MM-TII-MM_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/speech/node/wbamr/enc"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/speech/node/wbamr/enc

inherit ccasefetch tisocketnode
