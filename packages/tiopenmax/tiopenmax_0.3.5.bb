SECTION = "libs"
PRIORITY = "optional"
DEPENDS = "tidspbridge-lib"
DESCRIPTION = "Texas Instruments OpenMAX IL."
# FIXME: Three licenses are used, not just LGPL
LICENSE = "LGPL"
PR = "r0"

# We need to override this and make sure it's only -j1
PARALLEL_MAKE = "-j1"

inherit pkgconfig

#SRC_URI = "http://omapssp.dal.design.ti.com/VOBS/CSSD_Linux_Releases/3430/Linux_12.x/CSSD_Linux_${PV}RC1.tar.bz2 \
SRC_URI = "https://omapzoom.org/gf/download/frsrelease/170/1399/tiopenmax-${PV}.tar.gz \
	file://fmakefile-0.3.5 \
	file://libomxil-ti.pc \
	file://omap24xxvout.h"

do_compile_prepend() {
	install -m 0644 ${WORKDIR}/fmakefile-0.3.5 ${S}/Makefile
	install -m 0644 ${WORKDIR}/omap24xxvout.h ${S}/video/src/openmax_il/post_processor/inc/omap24xxvout.h
	install -m 0644 ${WORKDIR}/libomxil-ti.pc ${S}/libomxil.pc
}

do_compile() {
	oe_runmake V=1 BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR}
}

do_stage() {
	oe_libinstall -so -C ${S}/system/src/openmax_il/omx_core/src libOMX_Core ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/system/src/openmax_il/lcml/src libLCML ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/video/src/openmax_il/video_decode/src libOMX.TI.Video.Decoder ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/video/src/openmax_il/post_processor/src libOMX.TI.PostProc ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/audio/src/openmax_il/aac_dec/src libOMX.TI.AAC.decode ${STAGING_LIBDIR}
	install -d ${STAGING_INCDIR}/omx
	install -m 0644 ${S}/system/src/openmax_il/omx_core/inc/*.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/system/src/openmax_il/audio_manager/inc/AudioManagerAPI.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/system/src/openmax_il/clock_source/inc/OMX_Clock.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/video/src/openmax_il/post_processor/inc/OMX_PostProc_CustomCmd.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/audio/src/openmax_il/aac_dec/inc/TIDspOmx.h ${STAGING_INCDIR}/omx/
}

do_install() {
	oe_libinstall -so -C ${S}/system/src/openmax_il/omx_core/src libOMX_Core ${D}${libdir}
	oe_libinstall -so -C ${S}/system/src/openmax_il/lcml/src libLCML ${D}${libdir}
	oe_libinstall -so -C ${S}/video/src/openmax_il/video_decode/src libOMX.TI.Video.Decoder ${D}${libdir}
	oe_libinstall -so -C ${S}/video/src/openmax_il/post_processor/src libOMX.TI.PostProc ${D}${libdir}
	oe_libinstall -so -C ${S}/audio/src/openmax_il/aac_dec/src libOMX.TI.AAC.decode ${D}${libdir}
}

FILES_${PN} += "${libdir}/libOMX_Core.so \
	${libdir}/libLCML.so \
	${libdir}/libOMX.TI.Video.Decoder.so \
	${libdir}/libOMX.TI.PostProc.so \
	${libdir}/libOMX.TI.AAC.decode.so"
