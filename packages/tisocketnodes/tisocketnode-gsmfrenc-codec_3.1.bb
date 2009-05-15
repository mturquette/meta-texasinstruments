PRIORITY = "optional"
DESCRIPTION = "Texas Instruments GSM Full-Rate Encoder Socket Node Codec."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "baseimage"

CCASE_SPEC = "%\
	element /vobs/wtbu/CSSD_MM_Releases/Codecs/speech/fr_enc/c64x/mm_tiicodecs/... DSP-MM-TII-MM_RLS_${PV}%\
	element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/CSSD_MM_Releases/Codecs/speech/fr_enc/c64x/mm_tiicodecs/"
CCASE_PATHCOMPONENT = "mm_tiicodecs"
CCASE_PATHCOMPONENTS = "7"

inherit ccasefetch

do_compile() {
	unzip gsmfr_x_all_c64xplus.zip
}

do_stage() {
        chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/Codecs/speech/fr_enc/c64x/mm_tiicodecs/100_S_GSMFR_X_ALL_c64x+_1_05
	cp -a ${S}/100_S_GSMFR_X_ALL_c64x+_1_05/* ${STAGING_BINDIR}/dspbridge/Codecs/speech/fr_enc/c64x/mm_tiicodecs/100_S_GSMFR_X_ALL_c64x+_1_05
}
