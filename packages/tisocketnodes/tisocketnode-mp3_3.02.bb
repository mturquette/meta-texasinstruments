DESCRIPTION = "Texas Instruments MP3 Decoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-mp3-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/audio/node/mp3/dec/... DSP-MM-TII-MM_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/audio/node/mp3/dec"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/audio/node/mp3/dec

#set to release or debug
RELEASE = "release"

inherit ccasefetch tisocketnode
