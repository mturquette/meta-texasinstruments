DESCRIPTION = "Texas Instruments Real Video Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-rv89combodec-codec"

CCASE_SPEC = "%\
              element /vobs/wtbu/OMAPSW_DSP/video/node/rv89combo/dec/... DSP-MM-TII-MM_RLS_${PV}%\
              element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/video/node/rv89combo/dec"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/video/node/rv89combo/dec

inherit ccasefetch tisocketnode
