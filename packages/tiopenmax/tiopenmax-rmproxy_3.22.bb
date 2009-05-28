DEPENDS = "tidspbridge-lib tiopenmax-core"
DESCRIPTION = "Texas Instruments OpenMAX IL Resource Manager Proxy."
PR = "r1"
PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"

require tiopenmax-cspec-${PV}.inc

CCASE_PATHFETCH = "\
	/vobs/wtbu/OMAPSW_MPU/linux/system/src/openmax_il/resource_manager_proxy \
	/vobs/wtbu/OMAPSW_MPU/linux/Makefile \
	/vobs/wtbu/OMAPSW_MPU/linux/Master.mk \
	"
CCASE_PATHCOMPONENTS = 3
CCASE_PATHCOMPONENT = "linux"

#SRC_URI = "file://23.11-ampthread.patch;patch=1"

inherit ccasefetch

do_compile_prepend() {
	install -d ${D}/usr/lib
	install -d ${D}/usr/bin
}
#	install -d ${D}/omx

do_compile() {
	oe_runmake \
		PREFIX=${D}/usr PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		OMX_PERF_INSTRUMENTATION=1 OMX_PERF_CUSTOMIZABLE=1 \
		TARGETDIR=${D}/usr OMXROOT=${S} \
		OMXINCLUDEDIR=${STAGING_INCDIR}/omx \
		resource_manager_proxy
}

do_install() {
	oe_runmake \
		PREFIX=${D}/usr PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		OMX_PERF_INSTRUMENTATION=1 OMX_PERF_CUSTOMIZABLE=1 \
		TARGETDIR=${D}/usr OMXROOT=${S} \
		SYSTEMINCLUDEDIR=${D}/usr/include/omx \
		resource_manager_proxy.install
}

do_stage() {
	oe_runmake \
		PREFIX=${STAGING_DIR_TARGET}/usr PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		OMX_PERF_INSTRUMENTATION=1 OMX_PERF_CUSTOMIZABLE=1 \
		TARGETDIR=${STAGING_DIR_TARGET}/usr OMXROOT=${S} \
		SYSTEMINCLUDEDIR=${STAGING_INCDIR}/omx \
		resource_manager_proxy.install
}

FILES_${PN} = "\
	/usr/lib \
	/usr/bin \
	"
#	/omx \

FILES_${PN}-dbg = "\
	/usr/bin/.debug \
	/usr/lib/.debug \
	"
#	/omx/.debug \

FILES_${PN}-dev = "\
	/usr/include \
	"
