DESCRIPTION = "Texas Instruments MPEG4 Decoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-mpeg4dec-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/video/node/mpeg4/dec/... DSP-MM-TII-MM_RLS_${PV}%\
	      element /vobs/wtbu/OMAPSW_DSP/video/node/mpeg4/dec_pplib_configs/... DSP-MM-TII-IMVID_RLS_4.13%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/video/node/mpeg4/dec"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/video/node/mpeg4/dec

inherit ccasefetch tisocketnode
