DESCRIPTION = "Texas Instruments Real Audio Decoder Socket Node Codec."
LICENSE = "LGPL"
PR = "r0"

CCASE_SPEC = "%\
              element /vobs/wtbu/CSSD_MM_Releases/Codecs/audio/ragecko_dec/c64x/mm_tiicodecs/... DSP-MM-TII-AUDIO_RLS_${PV}%\
              element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/CSSD_MM_Releases/Codecs/audio/ragecko_dec/c64x/mm_tiicodecs"
CCASE_PATHCOMPONENT = "mm_tiicodecs"
CCASE_PATHCOMPONENTS = "7"

#SRC_URI = "file://mv_correction.patch;patch=1"

inherit ccasefetch

do_unpack2() {
        unzip a_ragecko_d_c64xplus_1_00.zip
}

do_stage() {
        chmod -R +w ${S}/*
        install -d ${STAGING_BINDIR}/dspbridge/Codecs/audio/ragecko_dec/c64x/mm_tiicodecs/A_RAGECKO_D_c64xplus_1_00
        cp -a ${S}/A_RAGECKO_D_c64xplus_1_00/* ${STAGING_BINDIR}/dspbridge/Codecs/audio/ragecko_dec/c64x/mm_tiicodecs/A_RAGECKO_D_c64xplus_1_00
}

addtask unpack2 after do_unpack_ccase before do_patch
