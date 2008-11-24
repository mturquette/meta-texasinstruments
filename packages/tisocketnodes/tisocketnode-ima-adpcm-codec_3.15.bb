PRIORITY = "optional"
DESCRIPTION = "Texas Instruments IMA-ADPCM Decoder Socket Node Codec."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "baseimage"

CCASE_SPEC = "%\
	element /vobs/wtbu/CSSD_MM_Releases/Codecs/audio/ima-adpcm_dec/c64x/mm_tiicodecs/... DSP-MM-TID-AUDIO_RLS_${PV}%\
	element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/CSSD_MM_Releases/Codecs/audio/ima-adpcm_dec/c64x/mm_tiicodecs/"
CCASE_PATHCOMPONENT = "mm_tiicodecs"
CCASE_PATHCOMPONENTS = "7"

#ENV_VAR = "DEPOT=${STAGING_BINDIR}/dspbridge/tools \
#	   DSPMAKEROOT=${STAGING_BINDIR}/dspbridge/make \
#	   DBS_BRIDGE_DIR_C64=${STAGING_BINDIR}/dspbridge/dsp \
#	   DBS_SABIOS_DIR_C64=${STAGING_BINDIR}/dspbridge/tools \
#	   DBS_CGTOOLS_DIR_C64=${STAGING_BINDIR}/dspbridge/tools/cgt6x-6.0.7 \
#	   DBS_FC=${STAGING_BINDIR}/dspbridge/dsp/bdsptools/framework_components_1_10_04/packages-bld \
#	   DLLCREATE_DIR=${STAGING_BINDIR}/DLLcreate \
#"

#set to release or debug
#RELEASE = "release"

inherit ccasefetch

do_compile() {
#	cd ${S}
	unzip C64XPLUS_IMA_ADPCM.zip
}

do_stage() {
	install -d ${STAGING_BINDIR}/dspbridge/Codecs/audio/ima-adpcm_dec/c64x/mm_tiicodecs/C64XPLUS_IMA_ADPCM
	cp -a ${S}/C64XPLUS_IMA_ADPCM/* ${STAGING_BINDIR}/dspbridge/Codecs/audio/ima-adpcm_dec/c64x/mm_tiicodecs/C64XPLUS_IMA_ADPCM
#	install -d ${STAGING_INCDIR}/dspbridge/exports/include
#	install -m 0644 ${S}/ti/dspbridge/dsp/bridge_product/exports/include/*.h ${STAGING_INCDIR}/dspbridge/exports/include
}

#do_install() {
#	install -d ${D}{libdir}/dsp
#	install -m 0644 ${S}/system/baseimage/out/omap3430/${RELEASE}/baseimage.dof ${D}${libdir}/dsp
#	install -m 0644 ${S}/system/baseimage/out/omap3430/${RELEASE}/baseimage.map ${D}${libdir}/dsp
#}
