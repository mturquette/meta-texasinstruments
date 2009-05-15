DESCRIPTION = "Texas Instruments PCM Decoder Socket Node."
PR = "r0"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/audio/node/pcm/dec/... DSP-MM-TII-MM_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/audio/node/pcm/dec"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/audio/node/pcm/dec

inherit ccasefetch tisocketnode
