DEPENDS = "tidspbridge-lib"
DESCRIPTION = "Texas Instruments Camera and ISP Algorithms."
LICENSE = "LGPL"
PR = "r1"
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

	# Since we can't do caf.install for now, manually install header
	install -m 0644 \
		${S}/linux/mm_isp/camera_algo_frmwk/inc/camera_alg.h ${STAGING_INCDIR}/mmisp/
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

	# Since we can't do caf.install for now, manually install header
	install -m 0644 \
		${S}/linux/mm_isp/camera_algo_frmwk/inc/camera_alg.h ${STAGING_INCDIR}/mmisp/
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
