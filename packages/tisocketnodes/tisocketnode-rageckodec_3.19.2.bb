DESCRIPTION = "Texas Instruments Real Audio Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-rageckodec-codec"

CCASE_SPEC = "%\
              element /vobs/wtbu/OMAPSW_DSP/audio/node/ra_gecko/dec/... DSP-MM-TII-AUDIO_RLS_${PV}%\
              element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/audio/node/ra_gecko/dec"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/audio/node/ra_gecko/dec

#SRC_URI = "file://mv_correction.patch;patch=1"

inherit ccasefetch tisocketnode
