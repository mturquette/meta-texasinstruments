DESCRIPTION = "Texas Instruments MP3 Decoder Socket Node Codec."
PR = "r0"

CCASE_SPEC = "%\
	element /vobs/wtbu/CSSD_MM_Releases/Codecs/audio/mp3_dec/c64x/mm_tiicodecs/... DSP-MM-TII-MM_RLS_${PV}%\
	element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/CSSD_MM_Releases/Codecs/audio/mp3_dec/c64x/mm_tiicodecs/"
CCASE_PATHCOMPONENT = "mm_tiicodecs"
CCASE_PATHCOMPONENTS = "7"

inherit ccasefetch

do_unpack2() {
	unzip 100_A_MP3_D_L3ONLY_c64x+_1_05.zip
}

do_stage() {
	install -d ${STAGING_BINDIR}/dspbridge/Codecs/audio/mp3_dec/c64x/mm_tiicodecs/100_A_MP3_D_L3ONLY_c64x+_1_05
	cp -a ${S}/100_A_MP3_D_L3ONLY_c64x+_1_05/* ${STAGING_BINDIR}/dspbridge/Codecs/audio/mp3_dec/c64x/mm_tiicodecs/100_A_MP3_D_L3ONLY_c64x+_1_05
}

addtask unpack2 after do_unpack_ccase before do_patch
