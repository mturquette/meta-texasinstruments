DESCRIPTION = "Texas Instruments OpenMAX IL Resource Manager."
DEPENDS = "tidspbridge-lib tiopenmax-core tiopenmax-ram tiopenmax-policymanager"
PR = "r0"
PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"

require tiopenmax-cspec-${PV}.inc
CCASE_PATHFETCH = "\
	/vobs/wtbu/OMAPSW_MPU/linux/system/src/openmax_il/resource_manager \
	/vobs/wtbu/OMAPSW_MPU/linux/Makefile \
	/vobs/wtbu/OMAPSW_MPU/linux/Master.mk \
	"
CCASE_PATHCOMPONENTS = 3
CCASE_PATHCOMPONENT = "linux"

SRC_URI = "\
	file://23.10-rmmakenocore.patch;patch=1 \
	file://23.10-rmmakenoram.patch;patch=1 \
	"

inherit ccasefetch

do_compile_prepend() {
	install -d ${D}/omx
	install -d ${D}/lib
	install -d ${D}/bin
}

do_compile() {
	cd ${S}/system/src/openmax_il/resource_manager
	oe_runmake \
		PREFIX=${D} PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${D} OMXROOT=${S} \
		OMXINCLUDEDIR=${STAGING_INCDIR}/omx \
		all
}

do_install() {
	cd ${S}/system/src/openmax_il/resource_manager
	oe_runmake \
		PREFIX=${D} PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${D} OMXROOT=${S} \
		SYSTEMINCLUDEDIR=${D}/include/omx \
		install
}

do_stage() {
	# Somehow, ${STAGING_DIR}/${HOST_SYS} != ${STAGING_LIBDIR}/../
	STAGE_DIR=${STAGING_LIBDIR}/../

	cd ${S}/system/src/openmax_il/resource_manager
	oe_runmake \
		PREFIX=${STAGE_DIR} PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${STAGE_DIR} OMXROOT=${S} \
		SYSTEMINCLUDEDIR=${STAGING_INCDIR}/omx \
		install
}

FILES_${PN} = "\
	/lib \
	/bin \
	/omx \
	"

FILES_${PN}-dbg = "\
	/omx/.debug \
	/bin/.debug \
	/lib/.debug \
	"

FILES_${PN}-dev = "\
	/include \
	"
