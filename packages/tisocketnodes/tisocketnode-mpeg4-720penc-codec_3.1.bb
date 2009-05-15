PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPEG-4 w/720p Decoder Socket Node Codec."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "baseimage"

CCASE_SPEC = "%\
	      element /vobs/wtbu/CSSD_MM_Releases/Codecs/video/mpeg4_ari_enc/... DSP-MM-TII-MM_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/CSSD_MM_Releases/Codecs/video/mpeg4_ari_enc"
CCASE_PATHCOMPONENT = "mpeg4_ari_enc"
CCASE_PATHCOMPONENTS = "5"

inherit ccasefetch

do_compile() {
}

do_stage() {
        chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/Codecs/video/mpeg4_ari_enc
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/Codecs/video/mpeg4_ari_enc
}
