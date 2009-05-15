DESCRIPTION = "Texas Instruments Sorensen Decoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-spark-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/video/node/spark/... DSP-MM-TII-MM_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/video/node/spark/dec"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/video/node/spark/dec

inherit ccasefetch tisocketnode
