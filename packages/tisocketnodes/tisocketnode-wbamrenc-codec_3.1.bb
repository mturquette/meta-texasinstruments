PRIORITY = "optional"
DESCRIPTION = "Texas Instruments WB-AMR Encoder Socket Node Codec."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "baseimage"

CCASE_SPEC = "%\
	element /vobs/wtbu/CSSD_MM_Releases/Codecs/speech/wbamr_enc/c64x/mm_tiicodecs/... DSP-MM-TII-MM_RLS_${PV}%\
	element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/CSSD_MM_Releases/Codecs/speech/wbamr_enc/c64x/mm_tiicodecs/"
CCASE_PATHCOMPONENT = "mm_tiicodecs"
CCASE_PATHCOMPONENTS = "7"

inherit ccasefetch

do_compile() {
	unzip 210_S_G722_2_X_ALL_c64x+_1_05.zip
}

do_stage() {
        chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/Codecs/speech/wbamr_enc/c64x/mm_tiicodecs/210_S_G722_2_X_ALL_c64x+_1_05
	cp -a ${S}/210_S_G722_2_X_ALL_c64x+_1_05/* ${STAGING_BINDIR}/dspbridge/Codecs/speech/wbamr_enc/c64x/mm_tiicodecs/210_S_G722_2_X_ALL_c64x+_1_05
}
