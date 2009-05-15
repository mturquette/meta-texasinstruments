DESCRIPTION = "Texas Instruments NB-AMR Encoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-nbamrenc-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/speech/node/nbamr/enc/... DSP-MM-TII-MM_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/speech/node/nbamr/enc"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/speech/node/nbamr/enc

inherit ccasefetch tisocketnode
