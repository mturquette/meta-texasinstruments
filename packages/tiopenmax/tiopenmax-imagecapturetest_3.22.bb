DESCRIPTION = "Texas Instruments OpenMAX IL Image Capture Test Application."
DEPENDS = "tidspbridge-lib tiopenmax-core tiopenmax-lcml tiopenmax-rmproxy tiopenmax-resourcemanager tiopenmax-rmproxy tiopenmax-postproc tiopenmax-prepostproc tiopenmax-jpegdec tiopenmax-camera"
PR = "r0"
PACKAGES = "${PN}-dbg ${PN}-patterns ${PN}-dev ${PN}"

require tiopenmax-cspec-${PV}.inc

CCASE_PATHFETCH = "\
	/vobs/wtbu/OMAPSW_MPU/linux/application/src/openmax_il/image_capture \
	/vobs/wtbu/OMAPSW_MPU/linux/Makefile \
	/vobs/wtbu/OMAPSW_MPU/linux/Master.mk \
	"
CCASE_PATHCOMPONENTS = 3
CCASE_PATHCOMPONENT = "linux"

SRC_URI = "\
	file://23.16-imagecapnocore.patch;patch=1 \
	${@base_contains("DISTRO_FEATURES", "testpatterns", "", "file://remove-patterns.patch;patch=1", d)} \
	"

inherit ccasefetch

do_compile_prepend() {
	install -d ${D}/usr/omx
	install -d ${D}/usr/lib
	install -d ${D}/usr/bin
}

do_compile() {
	cd ${S}/application/src/openmax_il/image_capture
	oe_runmake \
		PREFIX=${D}/usr PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${D}/usr OMXTESTDIR=${D}${bindir} OMXROOT=${S} OMXLIBDIR=${STAGING_LIBDIR} \
		OMXINCLUDEDIR=${STAGING_INCDIR}/omx \
		all
}

do_install() {
	cd ${S}/application/src/openmax_il/image_capture
	oe_runmake \
		PREFIX=${D}/usr PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${D}/usr OMXTESTDIR=${D}${bindir} OMXROOT=${S} \
		SYSTEMINCLUDEDIR=${D}/usr/include/omx \
		install
}

do_stage() {
	cd ${S}/application/src/openmax_il/image_capture
	oe_runmake \
		PREFIX=${STAGING_DIR_TARGET}/usr PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${STAGING_DIR_TARGET}/usr OMXTESTDIR=${STAGING_BINDIR} OMXROOT=${S} \
		SYSTEMINCLUDEDIR=${STAGING_INCDIR}/omx \
		install
}

FILES_${PN} = "\
	/usr/lib \
	/usr/bin \
	"

FILES_${PN}-patterns = "\
	/usr/omx/patterns \
	"

FILES_${PN}-dbg = "\
	/usr/bin/.debug \
	/usr/lib/.debug \
	"

FILES_${PN}-dev = "\
	/usr/include/omx \
	"

do_stage_rm_omxdir() {
	# Clean up undesired staging only if test patterns exist
	${@base_contains("DISTRO_FEATURES", "testpatterns", "rm -rf ${STAGING_DIR_TARGET}/usr/omx/", "echo nothing to do here!", d)}
}

do_install_cleanup() {
	# move test files out of /usr/bin/ to /usr/omx only if test patterns exist
	${@base_contains("DISTRO_FEATURES", "testpatterns", "mv ${D}${bindir}/video_decoder.* ${D}/usr/omx/patterns", "echo nothing to do here!", d)}
	${@base_contains("DISTRO_FEATURES", "testpatterns", "mv ${D}${bindir}/pinball_*.length ${D}/usr/omx/patterns", "echo nothing to do here!", d)}
	${@base_contains("DISTRO_FEATURES", "testpatterns", "mv ${D}${bindir}/monster_*.length ${D}/usr/omx/patterns", "echo nothing to do here!", d)}
}

addtask install_cleanup after do_install before do_package
addtask stage_rm_omxdir after do_populate_staging before do_package_stage
