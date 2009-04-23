DESCRIPTION = "Texas Instruments OpenMAX IL RAGECKO Decoder."
DEPENDS = "tidspbridge-lib tiopenmax-core tiopenmax-lcml tiopenmax-rmproxy tiopenmax-resourcemanager"
PR = "r0"
PACKAGES = "${PN}-dbg ${PN}-patterns ${PN}-dev ${PN}"

require tiopenmax-cspec-${PV}.inc
CCASE_PATHFETCH = "\
	/vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/ragecko_dec \
	/vobs/wtbu/OMAPSW_MPU/linux/video/src/openmaxil/rm_parser/inc \
	/vobs/wtbu/OMAPSW_MPU/linux/Makefile \
	/vobs/wtbu/OMAPSW_MPU/linux/Master.mk \
	"
CCASE_PATHCOMPONENTS = 3
CCASE_PATHCOMPONENT = "linux"

SRC_URI = " \
          file://23.14-rageckodecnocore.patch;patch=1 \
          file://23.14-rageckodectestnocore.patch;patch=1 \
         "

inherit ccasefetch

do_compile_prepend() {
	install -d ${D}/omx
	install -d ${D}/lib
	install -d ${D}/bin
	
}

do_compile() {
	cp  ${S}/video/src/openmax_il/rm_parser/inc/*.h ${STAGING_INCDIR}/omx/
	cd ${S}/audio/src/openmax_il/ragecko_dec
	rm inc/TIDspOmx.h
        cp ${STAGING_INCDIR}/omx/TIDspOmx.h inc/
	oe_runmake \
		PREFIX=${D} PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${D} OMXROOT=${S} OMXLIBDIR=${STAGING_LIBDIR} \
		OMXINCLUDEDIR=${STAGING_INCDIR}/omx \
		all
}

do_install() {
	cd ${S}/audio/src/openmax_il/ragecko_dec
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

	cd ${S}/audio/src/openmax_il/ragecko_dec
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

FILES_${PN}-patterns = "\
	/omx/patterns \
	"

FILES_${PN}-dbg = "\
	/omx/.debug \
	/bin/.debug \
	/lib/.debug \
	"

FILES_${PN}-dev = "\
	/include \
	"

do_stage_rm_omxdir() {
	# Somehow, ${STAGING_DIR}/${HOST_SYS} != ${STAGING_LIBDIR}/../
	STAGE_DIR=${STAGING_LIBDIR}/../

	
	# Clean up undesired staging
	rm -rf ${STAGE_DIR}/omx/
}

addtask install_cleanup after do_install before do_package
addtask stage_rm_omxdir after do_populate_staging before do_package_stage
