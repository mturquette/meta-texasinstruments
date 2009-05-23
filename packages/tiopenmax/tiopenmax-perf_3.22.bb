DEPENDS = "tidspbridge-lib"
DESCRIPTION = "Texas Instruments PERF instrumentation for OpenMAX IL."
PR = "r0"
PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"

require tiopenmax-cspec-${PV}.inc

CCASE_PATHFETCH = "\
	/vobs/wtbu/OMAPSW_MPU/linux/system/src/openmax_il/perf \
	/vobs/wtbu/OMAPSW_MPU/linux/Makefile \
	/vobs/wtbu/OMAPSW_MPU/linux/Master.mk \
	"
CCASE_PATHCOMPONENTS = 3
CCASE_PATHCOMPONENT = "linux"

inherit ccasefetch

do_compile_prepend() {
	install -d ${D}${bindir}
	install -d ${D}${libdir}
}

do_compile() {
	oe_runmake \
		PREFIX=${D}/usr PKGDIR=${S} \
		CROSS=${AR%-*}- OMXTESTDIR=${D}${bindir} \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge \
		BRIDGELIBDIR=${STAGING_LIBDIR} TARGETDIR=${D}/usr OMXROOT=${S} \
		OMX_PERF_INSTRUMENTATION=1 OMX_PERF_CUSTOMIZABLE=1 \
		perf
}

do_install() {
	oe_runmake \
		PREFIX=${D}/usr PKGDIR=${S} \
		CROSS=${AR%-*}- OMXTESTDIR=${D}${bindir} \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge \
		BRIDGELIBDIR=${STAGING_LIBDIR} TARGETDIR=${D}/usr OMXROOT=${S} \
		OMX_PERF_INSTRUMENTATION=1 OMX_PERF_CUSTOMIZABLE=1 \
		perf.install
}

do_stage() {
	oe_runmake \
		PREFIX=${STAGING_DIR_TARGET}/usr PKGDIR=${S} \
		CROSS=${AR%-*}- OMXTESTDIR=${STAGING_BINDIR}\
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${STAGING_DIR_TARGET}/usr OMXROOT=${S} \
		OMX_PERF_INSTRUMENTATION=1 OMX_PERF_CUSTOMIZABLE=1 \
		perf.install
}

FILES_${PN} = "\
	/usr/lib \
	/usr/bin \
	"

FILES_${PN}-dbg = "\
	/usr/bin/.debug \
	/usr/lib/.debug \
	"

FILES_${PN}-dev = "\
	/usr/include \
	"
