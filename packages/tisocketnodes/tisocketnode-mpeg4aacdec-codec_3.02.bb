PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPEG4 AAC Decoder Socket Node Codec."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "baseimage"

CCASE_SPEC = "%\
	element /vobs/wtbu/CSSD_MM_Releases/Codecs/audio/mpeg4aac_dec/c64x/mm_tiicodecs/... DSP-MM-TII-MM_RLS_${PV}%\
	element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/CSSD_MM_Releases/Codecs/audio/mpeg4aac_dec/c64x/mm_tiicodecs/"
CCASE_PATHCOMPONENT = "mm_tiicodecs"
CCASE_PATHCOMPONENTS = "7"

inherit ccasefetch

do_compile() {
	unzip 100_A_AAC_D_HE_c64x+_1_05.zip
}

do_stage() {
        chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/Codecs/audio/mpeg4aac_dec/c64x/mm_tiicodecs/100_A_AAC_D_HE_c64x+_1_05
	cp -a ${S}/100_A_AAC_D_HE_c64x+_1_05/* ${STAGING_BINDIR}/dspbridge/Codecs/audio/mpeg4aac_dec/c64x/mm_tiicodecs/100_A_AAC_D_HE_c64x+_1_05
}
