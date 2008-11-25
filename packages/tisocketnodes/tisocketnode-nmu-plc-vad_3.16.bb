<<<<<<< HEAD:packages/tisocketnodes/tisocketnode-nmu-plc-vad_3.16.bb
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments G711 Decoder/Encoder Socket Node nmu_plc_vad."
LICENSE = "LGPL"
=======
DESCRIPTION = "Texas Instruments G711 Decoder Socket Node nmu_plc_vad."
>>>>>>> origin:packages/tisocketnodes/tisocketnode-g711dec-nmu-plc-vad_3.15.bb
PR = "r0"
DEPENDS = "baseimage"

CCASE_SPEC = "%\
	      element /vobs/wtbu/CSSD_MM_Releases/Codecs/speech/nmu_plc_vad/... DSP-MM-TID-AUDIO_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/CSSD_MM_Releases/Codecs/speech/nmu_plc_vad"
CCASE_PATHCOMPONENT = "nmu_plc_vad"
CCASE_PATHCOMPONENTS = "5"

inherit ccasefetch

do_unpack2() {
	unzip nmu_plc_vad.zip
}

do_stage() {
	install -d ${STAGING_BINDIR}/dspbridge/Codecs/speech/nmu_plc_vad
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/Codecs/speech/nmu_plc_vad
}

addtask unpack2 after do_unpack_ccase before do_patch
