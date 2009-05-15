PRIORITY = "optional"
DESCRIPTION = "Texas Instruments WMA9 Decoder Socket Node Codec."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "baseimage"

CCASE_SPEC = "%\
	element /vobs/wtbu/CSSD_MM_Releases/Codecs/audio/wma9_dec/c64x/mm_tiicodecs/... DSP-MM-TII-MM_RLS_${PV}%\
	element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/CSSD_MM_Releases/Codecs/audio/wma9_dec/c64x/mm_tiicodecs/"
CCASE_PATHCOMPONENT = "mm_tiicodecs"
CCASE_PATHCOMPONENTS = "7"

inherit ccasefetch

do_compile() {
	unzip 100_A_WMA_D_STv9_c64x+_1_05.zip
}

do_stage() {
        chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/Codecs/audio/wma9_dec/c64x/mm_tiicodecs/100_A_WMA_D_STv9_c64x+_1_05
	cp -a ${S}/100_A_WMA_D_STv9_c64x+_1_05/* ${STAGING_BINDIR}/dspbridge/Codecs/audio/wma9_dec/c64x/mm_tiicodecs/100_A_WMA_D_STv9_c64x+_1_05
}
