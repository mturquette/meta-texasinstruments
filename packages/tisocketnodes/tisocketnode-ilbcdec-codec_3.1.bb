PRIORITY = "optional"
DESCRIPTION = "Texas Instruments iLBC Decoder Socket Node Codec."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "baseimage"

CCASE_SPEC = "%\
	element /vobs/wtbu/CSSD_MM_Releases/Codecs/speech/ilbc_dec/c64x/mm_tiicodecs/... DSP-MM-TII-MM_RLS_${PV}%\
	element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/CSSD_MM_Releases/Codecs/speech/ilbc_dec/c64x/mm_tiicodecs/"
CCASE_PATHCOMPONENT = "mm_tiicodecs"
CCASE_PATHCOMPONENTS = "7"

inherit ccasefetch

do_compile() {
	unzip 100_S_ILBC_X_ALL_c64x+_1_10.zip
}

do_stage() {
        chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/Codecs/speech/ilbc_dec/c64x/mm_tiicodecs/100_S_ILBC_X_ALL_c64x+_1_10
	cp -a ${S}/100_S_ILBC_X_ALL_c64x+_1_10/* ${STAGING_BINDIR}/dspbridge/Codecs/speech/ilbc_dec/c64x/mm_tiicodecs/100_S_ILBC_X_ALL_c64x+_1_10
}
