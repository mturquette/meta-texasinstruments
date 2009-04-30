DEPENDS = "tidspbridge-lib virtual/kernel"
DESCRIPTION = "Texas Instruments Camera and ISP Algorithms."
LICENSE = "LGPL"
PR = "r4"
PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"

# We need to override this and make sure it's only -j1
PARALLEL_MAKE = "-j1"

CCASE_SPEC = "\
	# MM ISP%\
	element /vobs/wtbu/OMAPSW_MPU/algo/... LINUX-TID-MMISP_RLS_${PV}%\
	element /vobs/wtbu/OMAPSW_MPU/linux/mm_isp/... LINUX-TID-MMISP_RLS_${PV}%\
	element * /main/LATEST%\
	"
CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_MPU/linux/mm_isp /vobs/wtbu/OMAPSW_MPU/algo"
CCASE_PATHCOMPONENTS = 2
CCASE_PATHCOMPONENT = "OMAPSW_MPU"

SRC_URI="\
	file://23.11-ippmk.patch;patch=1 \
	file://23.11-il3pmk.patch;patch=1 \
	file://23.11-cafmk.patch;patch=1 \
	"

inherit ccasefetch

do_compile() {
	# make'ing "all" will also install... let's be prepared
	install -d ${D}/bin
	install -d ${D}/lib
	install -d ${D}/include

	# We can't build any of this right now...
	#cd ${S}/algo/camera/vstab/make/linux/
	#oe_runmake -f Makefile.algo \
	#	ALGOROOT=${S}/algo \
	#	MMISPROOT=${S}/linux/mm_isp \
	#	KERNISPINCLUDEDIR=${STAGING_KERNEL_DIR}

	cd ${S}/linux/mm_isp/
	for action in clean all; do
		for subcomp in capl ipp il3p caf; do
			oe_runmake \
				ALGOROOT=${S}/algo \
				MMISPROOT=${S}/linux/mm_isp \
				KERNISPINCLUDEDIR=${STAGING_KERNEL_DIR} \
				PKGDIR=${S}/linux \
				CROSS=${AR%-*}- \
				BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge \
				BRIDGELIBDIR=${STAGING_LIBDIR} \
				TARGETDIR=${D} \
				$subcomp.$action
		done
	done
}

do_install() {
	install -d ${D}/lib
	install -d ${D}/bin
	install -d ${D}/include

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

	cd ${S}/algo/camera/isphal/make/linux/
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

	cd ${S}/algo/camera/3a/3a_interface/make/linux/
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
		capl.install ipp.install il3p.install caf.install
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
		TARGETDIR=${STAGING_DIR_TARGET}/usr \
		install

	cd ${S}/algo/camera/isphal/make/linux/
	oe_runmake \
		ALGOROOT=${S}/algo \
		MMISPROOT=${S}/linux/mm_isp \
		KERNISPINCLUDEDIR=${STAGING_KERNEL_DIR} \
		PKGDIR=${S}/linux \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge \
		BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${STAGING_LIBDIR} \
		install

	cd ${S}/algo/camera/3a/3a_interface/make/linux/
	oe_runmake \
		ALGOROOT=${S}/algo \
		MMISPROOT=${S}/linux/mm_isp \
		KERNISPINCLUDEDIR=${STAGING_KERNEL_DIR} \
		PKGDIR=${S}/linux \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge \
		BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${STAGING_LIBDIR} \
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
		TARGETDIR=${STAGING_DIR_TARGET}/usr/ \
		capl.install ipp.install il3p.install caf.install

	install -d ${STAGING_INCDIR}/capl/inc
	install -m 0644 ${S}/linux/mm_isp/capl/inc/*.h ${STAGING_INCDIR}/capl/inc/

	install -d ${STAGING_INCDIR}/mmisp
	install -m 0644 ${D}/include/mmisp/*.h ${STAGING_INCDIR}/mmisp/

	install -d ${STAGING_INCDIR}/camera_algo_frmwk/inc
	install -m 0644 ${S}/linux/mm_isp/camera_algo_frmwk/inc/*.h ${STAGING_INCDIR}/camera_algo_frmwk/inc/

	install -d ${STAGING_INCDIR}/ipp/inc
	install -m 0644 ${S}/linux/mm_isp/ipp/inc/*.h ${STAGING_INCDIR}/ipp/inc/

}

FILES_${PN} = "\
	/lib \
	/bin \
	"

FILES_${PN}-dbg = "\
	/lib/.debug \
	/bin/.debug \
	"

FILES_${PN}-dev = "\
	/include \
	"
