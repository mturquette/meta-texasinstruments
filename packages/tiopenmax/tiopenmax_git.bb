DEPENDS = "tidspbridge-lib"
DESCRIPTION = "Texas Instruments OpenMAX IL."
PR = "r1"
PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"

# We need to override this and make sure it's only -j1
PARALLEL_MAKE = "-j1"

inherit pkgconfig

SRCREV = "7bcdc0905e36c95742a686518c28ed518cf41fe2"
#	file://0002-Conditional-compilation-of-Android-specific-code.patch;patch=1 
SRC_URI = "\
	git://git.omapzoom.org/platform/hardware/ti/omx.git;protocol=git \
	file://fmakefile \
	file://omxcore-noandroidspecific.patch;patch=1 \
	file://aacdec-noandroidspecific.patch;patch=1 \
	file://mp3dec-noandroidspecific.patch;patch=1 \
	file://nbamrdec-noandroidspecific.patch;patch=1 \
	file://wbamrdec-noandroidspecific.patch;patch=1 \
	file://TIOMX_Core.c \
	"
S = "${WORKDIR}/git"

do_compile_prepend() {
	#install -m 0644 ${FILESDIR}/omap24xxvout.h ${S}/video/src/openmax_il/post_processor/inc/omap24xxvout.h
	install -m 0644 ${FILESDIR}/fmakefile ${S}/Makefile
	install -m 0644 ${FILESDIR}/libomxil-ti.pc ${S}/libomxil.pc
	install -m 0644 ${FILESDIR}/TIOMX_Core.c ${S}/system/src/openmax_il/omx_core/src/TIOMX_Core.c
	install -d ${D}/omx
}

do_compile() {
	oe_runmake \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge \
		BRIDGELIBDIR=${STAGING_LIBDIR}
}

do_stage() {
	oe_runmake \
		DESTDIR=${STAGING_LIBDIR}/.. \
		install
	install -d ${STAGING_INCDIR}/omx
	install -m 0644 ${S}/system/src/openmax_il/omx_core/inc/*.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/audio/src/openmax_il/aac_dec/inc/TIDspOmx.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/system/src/openmax_il/common/inc/OMX_TI_Common.h ${STAGING_INCDIR}/omx/
}

do_install() {
	oe_runmake \
		DESTDIR=${D} \
		install
	install -d ${D}/include/omx
	install -m 0644 ${S}/system/src/openmax_il/omx_core/inc/*.h ${D}/include/omx/
	install -m 0644 ${S}/audio/src/openmax_il/aac_dec/inc/TIDspOmx.h ${D}/include/omx/
	install -m 0644 ${S}/system/src/openmax_il/common/inc/OMX_TI_Common.h ${D}/include/omx/
}

FILES_${PN} = "\
	/lib \
	"

FILES_${PN}-dbg = "\
	/lib/.debug \
	"

FILES_${PN}-dev = "\
	/include \
	"
