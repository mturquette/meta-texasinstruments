PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPEG4 w/720p Decoder Socket Node Codec."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "baseimage"

CCASE_SPEC = "%\
	      element /vobs/wtbu/CSSD_MM_Releases/Codecs/video/DivX_3430/... DSP-MM-TII-MM_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/CSSD_MM_Releases/Codecs/video/DivX_3430"
CCASE_PATHCOMPONENT = "DivX_3430"
CCASE_PATHCOMPONENTS = "5"

inherit ccasefetch

do_compile() {
}

do_stage() {
        chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/Codecs/video/DivX_3430
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/Codecs/video/DivX_3430
}
