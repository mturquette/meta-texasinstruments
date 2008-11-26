DEPENDS = "tidspbridge-lib"
DESCRIPTION = "Texas Instruments Camera and ISP Algorithms."
LICENSE = "LGPL"
PR = "r0"
PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"

# We need to override this and make sure it's only -j1
PARALLEL_MAKE = "-j1"

CCASE_SPEC = "\
	# MM ISP%\
	element /vobs/wtbu/OMAPSW_MPU/algo/... LINUX-TID-MMISP_RLS_1.11%\
	element /vobs/wtbu/OMAPSW_MPU/linux/mm_isp/... LINUX-TID-MMISP_RLS_1.11%\
	\
	element * /main/LATEST%\
	"
CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_MPU/linux/mm_isp /vobs/wtbu/OMAPSW_MPU/algo"
CCASE_PATHCOMPONENTS = 2
CCASE_PATHCOMPONENT = "OMAPSW_MPU"

SRC_URI="\
	file://23.11-ippmk.patch;patch=1 \
	file://23.11-cafmk.patch;patch=1 \
	"

inherit ccasefetch

do_compile() {
	# make'ing "all" will also install... let's be prepared
	install -d ${D}/lib
	install -d ${D}/include

	# We can't build this right now
	#cd ${S}/algo/camera/vstab/make/linux/
	#oe_runmake -f Makefile.algo \
		ALGOROOT=${S}/algo \
		MMISPROOT=${S}/linux/mm_isp \
		KERNISPINCLUDEDIR=${STAGING_KERNEL_DIR}

	cd ${S}/linux/mm_isp/
	oe_runmake \
		ALGOROOT=${S}/algo \
		MMISPROOT=${S}/linux/mm_isp \
		KERNISPINCLUDEDIR=${STAGING_KERNEL_DIR} \
		PKGDIR=${S}/linux \
		CROSS=${AR%-*}- \
		capl.clean ipp.clean #caf.clean

	oe_runmake \
		ALGOROOT=${S}/algo \
		MMISPROOT=${S}/linux/mm_isp \
		KERNISPINCLUDEDIR=${STAGING_KERNEL_DIR} \
		PKGDIR=${S}/linux \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge \
		BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${D} \
		capl.all ipp.all #caf.all
}

do_stagea() {
	# FIXME: more headers are needed
	# TODO: can we make install to stagingdir?
	oe_libinstall -so -C ${S}/system/src/openmax_il/omx_core/src libOMX_Core ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/system/src/openmax_il/lcml/src libLCML ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/system/src/openmax_il/clock_source/src libOMX_Clock ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/system/src/openmax_il/resource_manager_proxy/src libOMX_ResourceManagerProxy ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/system/src/openmax_il/resource_manager/resource_activity_monitor/src libRAM ${STAGING_LIBDIR}
	install -d ${STAGING_INCDIR}/omx
	install -m 0644 ${S}/system/src/openmax_il/omx_core/inc/*.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/system/src/openmax_il/audio_manager/inc/AudioManagerAPI.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/system/src/openmax_il/clock_source/inc/OMX_Clock.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/video/src/openmax_il/post_processor/inc/OMX_PostProc_CustomCmd.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/audio/src/openmax_il/aac_dec/inc/TIDspOmx.h ${STAGING_INCDIR}/omx/
}

do_install() {
	install -d ${D}/lib

	cd ${S}/algo/camera/vstab/make/linux/
	oe_runmake \
		ALGOROOT=${S}/algo \
		MMISPROOT=${S}/linux/mm_isp \
		KERNISPINCLUDEDIR=${STAGING_KERNEL_DIR} \
		PKGDIR=${S}/linux \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge \
		BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${D} \
		install

	cd ${S}/linux/mm_isp/
	oe_runmake \
		ALGOROOT=${S}/algo \
		MMISPROOT=${S}/linux/mm_isp \
		KERNISPINCLUDEDIR=${STAGING_KERNEL_DIR} \
		PKGDIR=${S}/linux \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge \
		BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${D} \
		capl.install ipp.install #caf.install
}

do_stage() {
	install -d ${STAGING_LIBDIR}

	cd ${S}/algo/camera/vstab/make/linux/
	oe_runmake \
		ALGOROOT=${S}/algo \
		MMISPROOT=${S}/linux/mm_isp \
		KERNISPINCLUDEDIR=${STAGING_KERNEL_DIR} \
		PKGDIR=${S}/linux \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge \
		BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${STAGING_LIBDIR}/../ \
		install

	cd ${S}/linux/mm_isp/
	oe_runmake \
		ALGOROOT=${S}/algo \
		MMISPROOT=${S}/linux/mm_isp \
		KERNISPINCLUDEDIR=${STAGING_KERNEL_DIR} \
		PKGDIR=${S}/linux \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge \
		BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${STAGING_LIBDIR}/../ \
		capl.install ipp.install #caf.install
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
