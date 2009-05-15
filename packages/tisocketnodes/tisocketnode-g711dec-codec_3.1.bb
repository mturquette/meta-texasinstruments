DESCRIPTION = "Texas Instruments G711 Decoder Socket Node Codec."
PR = "r0"

CCASE_SPEC = "%\
	      element /vobs/wtbu/CSSD_MM_Releases/Codecs/speech/g711_dec/c64x/mm_tiicodecs/... DSP-MM-TII-MM_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/CSSD_MM_Releases/Codecs/speech/g711_dec/c64x/mm_tiicodecs"
CCASE_PATHCOMPONENT = "mm_tiicodecs"
CCASE_PATHCOMPONENTS = "7"

inherit ccasefetch

do_unpack2() {
	unzip g711_x_all_c64xplus.zip
}

do_stage() {
	chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/Codecs/speech/g711_dec/c64x/mm_tiicodecs/g711_x_all_c64xplus
	cp -a ${S}/g711_x_all_c64xplus/* ${STAGING_BINDIR}/dspbridge/Codecs/speech/g711_dec/c64x/mm_tiicodecs/g711_x_all_c64xplus
}

addtask unpack2 after do_unpack_ccase before do_patch
