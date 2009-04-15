DESCRIPTION = "Texas Instruments JPEG Decoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-jpegdec-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/image/node/jpeg/dec/... DSP-MM-TII-IMVID_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/image/node/jpeg/dec"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/image/node/jpeg/dec

#set to release or debug
RELEASE = "release"

inherit ccasefetch tisocketnode
