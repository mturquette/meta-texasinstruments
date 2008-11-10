PRIORITY = "optional"
DEPENDS = "tidspbridge-lib"
DESCRIPTION = "Texas Instruments OpenMAX IL."
LICENSE = "LGPL"
PR = "r1"
#FIXME NOT COMPLETED
S = ${WORKDIR}/src/omx/linux

inherit pkgconfig ccasefetch

SRC_URI = " \
	file://fmakefile \
	file://libomxil-ti.pc \
	file://omap24xxvout.h \
	file://aacdecnorm.patch;patch=2 \
	file://aacencnorm.patch;patch=1 \
	file://nbamrdecnorm.patch;patch=1 \
	file://nbamrencnorm.patch;patch=1 \
	file://wbamrdecnorm.patch;patch=1 \
	file://wbamrencnorm.patch;patch=1 \
	file://postprocnorm.patch;patch=1 \
	file://videoencnorm.patch;patch=1 \
	file://vppnorm.patch;patch=1 \
	file://12.20-addram.patch;patch=1 \
	file://12.20-addpm.patch;patch=1"
#file://aacdecpcomp.patch;patch=1 \

CCASE_SPEC = "%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/...		LINUX-MMAUDIO_RLS_3.16%\
	element /vobs/wtbu/OMAPSW_MPU/linux/system/...		LINUX-MMSYSTEM_RLS_3.16%\
	element /vobs/wtbu/OMAPSW_MPU/linux/video/...		LINUX-MMVIDEO_RLS_3.16%\
	element /vobs/wtbu/OMAPSW_MPU/linux/image/...		LINUX-MMIMAGE_RLS_3.16%\
	element /vobs/wtbu/OMAPSW_MPU/linux/application/...	LINUX-MMAPPLICATION_RLS_3.16%\
	element /vobs/wtbu/OMAPSW_MPU/linux/...				LINUX-MMROOT_RLS_3.16%\
	element * /main/LATEST%\
	"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_MPU/linux"
CCASE_PATHCOMPONENT = "linux"
CCASE_PATHCOMPONENTS = 3

#do_unpack() {
#	cd ${WORKDIR}
#	tar jxf ${DL_DIR}/CSSD_Linux_${PV}RC1.tar.bz2 CSSD_Linux_${PV}/src/omx/linux --strip-components 1
#}

do_compile_prepend() {
	install -m 0644 ${FILESDIR}/fmakefile ${S}/Makefile
	install -m 0644 ${FILESDIR}/omap24xxvout.h ${S}/video/src/openmax_il/post_processor/inc/omap24xxvout.h
	install -m 0644 ${FILESDIR}/libomxil-ti.pc ${S}/libomxil.pc
}

do_compile() {
	oe_runmake BRIDGE_DIR=${STAGING_LIBDIR} INCLUDE_DIR=${STAGING_INCDIR}
}

do_stage() {
	oe_libinstall -so -C ${S}/system/src/openmax_il/omx_core/src libOMX_Core ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/system/src/openmax_il/lcml/src libLCML ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/system/src/openmax_il/clock_source/src libOMX_Clock ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/system/src/openmax_il/resource_manager_proxy/src libOMX_ResourceManagerProxy ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/system/src/openmax_il/resource_activity_monitor/src libRAM ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/video/src/openmax_il/video_decode/src libOMX.TI.Video.Decoder ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/video/src/openmax_il/video_encode/src libOMX.TI.Video.encoder ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/video/src/openmax_il/post_processor/src libOMX.TI.PostProc ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/video/src/openmax_il/prepost_processor/src libOMX.TI.VPP ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/image/src/openmax_il/jpeg_dec/src libOMX.TI.JPEG.decoder ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/image/src/openmax_il/jpeg_enc/src libOMX.TI.JPEG.encoder ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/audio/src/openmax_il/mp3_dec/src libOMX.TI.MP3.decode ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/audio/src/openmax_il/aac_dec/src libOMX.TI.AAC.decode ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/audio/src/openmax_il/aac_enc/src libOMX.TI.AAC.encode ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/audio/src/openmax_il/nbamr_dec/src libOMX.TI.AMR.decode ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/audio/src/openmax_il/nbamr_enc/src libOMX.TI.AMR.encode ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/audio/src/openmax_il/wbamr_dec/src libOMX.TI.WBAMR.decode ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/audio/src/openmax_il/wbamr_enc/src libOMX.TI.WBAMR.encode ${STAGING_LIBDIR}
	install -d ${STAGING_INCDIR}/omx
	install -m 0644 ${S}/system/src/openmax_il/omx_core/inc/*.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/system/src/openmax_il/audio_manager/inc/AudioManagerAPI.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/system/src/openmax_il/clock_source/inc/OMX_Clock.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/video/src/openmax_il/post_processor/inc/OMX_PostProc_CustomCmd.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/audio/src/openmax_il/aac_dec/inc/TIDspOmx.h ${STAGING_INCDIR}/omx/
	install -d ${STAGING_BINDIR}
	install -m 0755 ${S}/system/src/openmax_il/audio_manager/src/OMXAudioManager ${STAGING_BINDIR}
	install -m 0755 ${S}/system/src/openmax_il/resource_manager/src/OMXResourceManager ${STAGING_BINDIR}
	install -m 0755 ${S}/system/src/openmax_il/omx_policy_manager/src/OMXPolicyManager ${STAGING_BINDIR}
}

do_install() {
	oe_libinstall -so -C ${S}/system/src/openmax_il/omx_core/src libOMX_Core ${D}${libdir}
	oe_libinstall -so -C ${S}/system/src/openmax_il/lcml/src libLCML ${D}${libdir}
	oe_libinstall -so -C ${S}/system/src/openmax_il/clock_source/src libOMX_Clock ${D}${libdir}
	oe_libinstall -so -C ${S}/system/src/openmax_il/resource_manager_proxy/src libOMX_ResourceManagerProxy ${D}${libdir}
	oe_libinstall -so -C ${S}/system/src/openmax_il/resource_activity_monitor/src libRAM ${D}${libdir}
	oe_libinstall -so -C ${S}/video/src/openmax_il/video_decode/src libOMX.TI.Video.Decoder ${D}${libdir}
	oe_libinstall -so -C ${S}/video/src/openmax_il/video_encode/src libOMX.TI.Video.encoder ${D}${libdir}
	oe_libinstall -so -C ${S}/video/src/openmax_il/post_processor/src libOMX.TI.PostProc ${D}${libdir}
	oe_libinstall -so -C ${S}/video/src/openmax_il/prepost_processor/src libOMX.TI.VPP ${D}${libdir}
	oe_libinstall -so -C ${S}/image/src/openmax_il/jpeg_dec/src libOMX.TI.JPEG.decoder ${D}${libdir}
	oe_libinstall -so -C ${S}/image/src/openmax_il/jpeg_enc/src libOMX.TI.JPEG.encoder ${D}${libdir}
	oe_libinstall -so -C ${S}/audio/src/openmax_il/mp3_dec/src libOMX.TI.MP3.decode ${D}${libdir}
	oe_libinstall -so -C ${S}/audio/src/openmax_il/aac_dec/src libOMX.TI.AAC.decode ${D}${libdir}
	oe_libinstall -so -C ${S}/audio/src/openmax_il/aac_enc/src libOMX.TI.AAC.encode ${D}${libdir}
	oe_libinstall -so -C ${S}/audio/src/openmax_il/nbamr_dec/src libOMX.TI.AMR.decode ${D}${libdir}
	oe_libinstall -so -C ${S}/audio/src/openmax_il/nbamr_enc/src libOMX.TI.AMR.encode ${D}${libdir}
	oe_libinstall -so -C ${S}/audio/src/openmax_il/wbamr_dec/src libOMX.TI.WBAMR.decode ${D}${libdir}
	oe_libinstall -so -C ${S}/audio/src/openmax_il/wbamr_enc/src libOMX.TI.WBAMR.encode ${D}${libdir}
	install -d ${D}${bindir}
	install -m 0755 ${S}/system/src/openmax_il/audio_manager/src/OMXAudioManager ${D}${bindir}
	install -m 0755 ${S}/system/src/openmax_il/resource_manager/src/OMXResourceManager ${D}${bindir}
	install -m 0755 ${S}/system/src/openmax_il/omx_policy_manager/src/OMXPolicyManager ${D}${bindir}
}

FILES_${PN} += "\
	${bindir}/OMXAudioManager \
	${bindir}/OMXResourceManager \
	${bindir}/OMXPolicyManager \
	${libdir}/libOMX_Core.so \
	${libdir}/libLCML.so \
	${libdir}/libOMX_ResourceManagerProxy.so \
	${libdir}/libRAM.so \
	${libdir}/libOMX_Clock.so \
	${libdir}/libOMX.TI.Video.Decoder.so \
	${libdir}/libOMX.TI.Video.encoder.so \
	${libdir}/libOMX.TI.PostProc.so \
	${libdir}/libOMX.TI.VPP.so \
	${libdir}/libOMX.TI.JPEG.decoder.so \
	${libdir}/libOMX.TI.JPEG.encoder.so \
	${libdir}/libOMX.TI.MP3.decode.so \
	${libdir}/libOMX.TI.AAC.decode.so \
	${libdir}/libOMX.TI.AAC.encode.so \
	${libdir}/libOMX.TI.AMR.decode.so \
	${libdir}/libOMX.TI.AMR.encode.so \
	${libdir}/libOMX.TI.WBAMR.decode.so \
	${libdir}/libOMX.TI.WBAMR.encode.so"
