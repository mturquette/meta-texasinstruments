DESCRIPTION = "Texas Instruments Real Video Decoder Socket Node Codec."
LICENSE = "LGPL"
PR = "r0"

CCASE_SPEC = "%\
              element /vobs/wtbu/CSSD_MM_Releases/Codecs/video/rv89combo_dec/c64x/mm_tiicodecs/... DSP-MM-TII-MM_RLS_${PV}%\
              element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/CSSD_MM_Releases/Codecs/video/rv89combo_dec/c64x/mm_tiicodecs"
CCASE_PATHCOMPONENT = "mm_tiicodecs"
CCASE_PATHCOMPONENTS = "7"

inherit ccasefetch

do_unpack2() {
        unzip rv_d_c64xplus_1_04.zip
}

do_stage() {
        chmod -R +w ${S}/*
        install -d ${STAGING_BINDIR}/dspbridge/Codecs/video/rv89combo_dec/c64x/mm_tiicodecs/rv_d_c64xplus_1_04
        cp -a ${S}/rv_d_c64xplus_1_04/* ${STAGING_BINDIR}/dspbridge/Codecs/video/rv89combo_dec/c64x/mm_tiicodecs/rv_d_c64xplus_1_04
}

addtask unpack2 after do_unpack_ccase before do_patch
