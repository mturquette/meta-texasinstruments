PRIORITY = "optional"
DESCRIPTION = "Texas Instruments IMA-ADPCM Encoder Socket Node Codec."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "baseimage"

CCASE_SPEC = "%\
	element /vobs/wtbu/CSSD_MM_Releases/Codecs/audio/ima-adpcm_enc/c64x/mm_tiicodecs/... DSP-MM-TII-MM_RLS_${PV}%\
	element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/CSSD_MM_Releases/Codecs/audio/ima-adpcm_enc/c64x/mm_tiicodecs/"
CCASE_PATHCOMPONENT = "mm_tiicodecs"
CCASE_PATHCOMPONENTS = "7"

inherit ccasefetch

do_compile() {
	unzip C64XPLUS_IMA_ADPCM_ENC.zip
}

do_stage() {
        chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/Codecs/audio/ima-adpcm_enc/c64x/mm_tiicodecs/C64XPLUS_IMA_ADPCM
	cp -a ${S}/C64XPLUS_IMA_ADPCM/* ${STAGING_BINDIR}/dspbridge/Codecs/audio/ima-adpcm_enc/c64x/mm_tiicodecs/C64XPLUS_IMA_ADPCM
}
