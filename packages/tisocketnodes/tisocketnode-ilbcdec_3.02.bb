DESCRIPTION = "Texas Instruments iLBC Decoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-ilbcdec-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/speech/node/iLBC/dec/... DSP-MM-TII-MM_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/speech/node/iLBC/dec"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/speech/node/iLBC/dec

inherit ccasefetch tisocketnode
