DEPENDS = "tidspbridge-lib tiopenmax-core"
DESCRIPTION = "Texas Instruments Linux Common Media Library for OpenMAX IL."
PR = "r0"
PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"

require tiopenmax-cspec-${PV}.inc

CCASE_PATHFETCH = "\
	/vobs/wtbu/OMAPSW_MPU/linux/system/src/openmax_il/lcml \
	/vobs/wtbu/OMAPSW_MPU/linux/Makefile \
	/vobs/wtbu/OMAPSW_MPU/linux/Master.mk \
	"
CCASE_PATHCOMPONENTS = 3
CCASE_PATHCOMPONENT = "linux"

SRC_URI = "file://23.11-lcmlnocore.patch;patch=1"

inherit ccasefetch

do_compile_prepend() {
	install -d ${D}/usr/omx
	install -d ${D}/usr/lib
	install -d ${D}/usr/bin
}

do_compile() {
	oe_runmake \
		PREFIX=${D}/usr PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${D}/usr OMXROOT=${S} \
		OMXINCLUDEDIR=${STAGING_INCDIR}/omx \
		lcml
}

do_install() {
	oe_runmake \
		PREFIX=${D}/usr PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${D}/usr OMXROOT=${S} \
		lcml.install
}

do_stage() {
	oe_runmake \
		PREFIX=${STAGING_DIR_TARGET}/usr PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${STAGING_DIR_TARGET}/usr OMXROOT=${S} \
		lcml.install
}

FILES_${PN} = "\
	/usr/lib \
	/usr/bin \
	"
#	/usr/omx \

FILES_${PN}-dbg = "\
	/usr/bin/.debug \
	/usr/lib/.debug \
	"
#	/usr/omx/.debug \

FILES_${PN}-dev = "\
	/usr/include \
	"
