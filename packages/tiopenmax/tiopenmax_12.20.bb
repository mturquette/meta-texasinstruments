PRIORITY = "optional"
DEPENDS = "tidspbridge-lib"
DESCRIPTION = "Texas Instruments OpenMAX IL."
LICENSE = "LGPL"
PR = "r1"

S = ${WORKDIR}/src/omx/linux

inherit pkgconfig

SRC_URI = "http://omapssp.dal.design.ti.com/VOBS/CSSD_Linux_Releases/3430/Linux_12.x/CSSD_Linux_${PV}RC1.tar.bz2;md5sum=a8adec991beedf4e4f8dac24cb518a5b \
	file://fmakefile \
	file://libomxil-ti.pc \
	file://omap24xxvout.h \
	file://aacdecpcomp.patch;patch=1 \
	file://postprocnorm.patch;patch=1 \
	file://aacdecnorm.patch;patch=1 \
	file://12.20-addram.patch;patch=1 \
	file://12.20-addpm.patch;patch=1"

do_unpack() {
	cd ${WORKDIR}
	tar jxf ${DL_DIR}/CSSD_Linux_${PV}RC1.tar.bz2 CSSD_Linux_${PV}/src/omx/linux --strip-components 1
}

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
	oe_libinstall -so -C ${S}/video/src/openmax_il/video_decode/src libOMX.TI.Video.Decoder ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/video/src/openmax_il/post_processor/src libOMX.TI.PostProc ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/audio/src/openmax_il/mp3_dec/src libOMX.TI.MP3.decode ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/audio/src/openmax_il/aac_dec/src libOMX.TI.AAC.decode ${STAGING_LIBDIR}
	install -d ${STAGING_INCDIR}/omx
	install -m 0644 ${S}/system/src/openmax_il/omx_core/inc/*.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/system/src/openmax_il/audio_manager/inc/AudioManagerAPI.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/system/src/openmax_il/clock_source/inc/OMX_Clock.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/video/src/openmax_il/post_processor/inc/OMX_PostProc_CustomCmd.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/audio/src/openmax_il/aac_dec/inc/TIDspOmx.h ${STAGING_INCDIR}/omx/
	install -d ${STAGING_BINDIR}
	install -m 0644 ${S}/system/src/openmax_il/audio_manager/src/OMXAudioManager ${STAGING_BINDIR}
	install -m 0644 ${S}/system/src/openmax_il/resource_manager/src/OMXResourceManager ${STAGING_BINDIR}
	install -m 0644 ${S}/system/src/openmax_il/omx_policy_manager/src/OMXPolicyManager ${STAGING_BINDIR}
}

do_install() {
	oe_libinstall -so -C ${S}/system/src/openmax_il/omx_core/src libOMX_Core ${D}${libdir}
	oe_libinstall -so -C ${S}/system/src/openmax_il/lcml/src libLCML ${D}${libdir}
	oe_libinstall -so -C ${S}/system/src/openmax_il/clock_source/src libOMX_Clock ${D}${libdir}
	oe_libinstall -so -C ${S}/system/src/openmax_il/resource_manager_proxy/src libOMX_ResourceManagerProxy ${D}${libdir}
	oe_libinstall -so -C ${S}/video/src/openmax_il/video_decode/src libOMX.TI.Video.Decoder ${D}${libdir}
	oe_libinstall -so -C ${S}/video/src/openmax_il/post_processor/src libOMX.TI.PostProc ${D}${libdir}
	oe_libinstall -so -C ${S}/audio/src/openmax_il/mp3_dec/src libOMX.TI.MP3.decode ${D}${libdir}
	oe_libinstall -so -C ${S}/audio/src/openmax_il/aac_dec/src libOMX.TI.AAC.decode ${D}${libdir}
	install -d ${D}${bindir}
	install -m 0644 ${S}/system/src/openmax_il/audio_manager/src/OMXAudioManager ${D}${bindir}
	install -m 0644 ${S}/system/src/openmax_il/resource_manager/src/OMXResourceManager ${D}${bindir}
	install -m 0644 ${S}/system/src/openmax_il/omx_policy_manager/src/OMXPolicyManager ${D}${bindir}
}

FILES_${PN} += "\
	${bindir}/OMXAudioManager \
	${bindir}/OMXResourceManager \
	${bindir}/OMXPolicyManager \
	${libdir}/libOMX_Core.so \
	${libdir}/libLCML.so \
	${libdir}/libOMX_ResourceManagerProxy.so \
	${libdir}/libOMX_Clock.so \
	${libdir}/libOMX.TI.Video.Decoder.so \
	${libdir}/libOMX.TI.PostProc.so \
	${libdir}/libOMX.TI.MP3.decode.so \
	${libdir}/libOMX.TI.AAC.decode.so"
