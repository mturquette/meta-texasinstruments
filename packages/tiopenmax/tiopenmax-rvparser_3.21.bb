DEPENDS = "tidspbridge-lib tiopenmax-core"
DESCRIPTION = "Texas Instruments OpenMAX IL RV Parser"
PR = "r0"
PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"

require tiopenmax-cspec-${PV}.inc

CCASE_PATHFETCH = "\
	/vobs/wtbu/OMAPSW_MPU/linux/video/src/openmax_il/rm_rvparser \
	/vobs/wtbu/OMAPSW_MPU/linux/Makefile \
	/vobs/wtbu/OMAPSW_MPU/linux/Master.mk \
	"
CCASE_PATHCOMPONENTS = 3
CCASE_PATHCOMPONENT = "linux"

SRC_URI = " \
	${@base_contains("DISTRO_FEATURES", "testpatterns", "", "file://remove-patterns.patch;patch=1", d)} \
	"

inherit ccasefetch

do_compile_prepend() {
	install -d ${D}/usr/lib
	install -d ${D}/usr/bin
}
#	install -d ${D}/omx

do_compile() {
	cd ${S}/video/src/openmax_il/rm_rvparser
	oe_runmake \
		PREFIX=${D}/usr PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${D}/usr OMXROOT=${S} OMXLIBDIR=${STAGING_LIBDIR} \
		OMXINCLUDEDIR=${STAGING_INCDIR}/omx \
		RVPARSERINCLUDEDIR=${S}/video/src/openmax_il/rm_rvparser/inc \
		all
}

do_install() {
	cd ${S}/video/src/openmax_il/rm_rvparser
	oe_runmake \
		PREFIX=${D}/usr PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${D}/usr OMXROOT=${S} OMXLIBDIR=${STAGING_LIBDIR} \
		SYSTEMINCLUDEDIR=${D}/usr/include/omx \
		RVPARSERINCLUDEDIR=${S}/video/src/openmax_il/rm_rvparser/inc \
		install
}

do_stage() {
	cd ${S}/video/src/openmax_il/rm_rvparser
	oe_runmake \
		PREFIX=${STAGING_DIR_TARGET}/usr PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${STAGING_DIR_TARGET}/usr OMXROOT=${S} OMXLIBDIR=${STAGING_LIBDIR} \
		SYSTEMINCLUDEDIR=${STAGING_INCDIR}/omx \
		RVPARSERINCLUDEDIR=${S}/video/src/openmax_il/rm_rvparser/inc \
		install
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
