SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU/DSP Bridge."
LICENSE = "LGPL"
PR = "r1"
SRC_URI = "http://omapssp.dal.design.ti.com/VOBS/CSSD_Linux_Releases/3430/Linux_12.x/CSSD_Linux_12.20RC1.tar.bz2 \
	file://mkcross-driver.patch;patch=1 \
	file://mkcross-api.patch;patch=1"
COMPATIBLE_MACHINE = "omap-3430ldp"
S = ${WORKDIR}/CSSD_Linux_12.20/src/bridge/mpu

#		KRNLSRC=${STAGING_DIR}/omap-3430ldp-poky-linux-gnueabi/kernel \

do_compile_driver() {
	cd ${S}/mpu_driver/src
	oe_runmake PREFIX=${S} PROJROOT=${S}/mpu_driver \
		KRNLSRC=${STAGING_KERNEL_DIR} \
		TGTROOT=${S} BUILD=rel CMDDEFS='GT_TRACE DEBUG' \
		CROSS=${AR%-*}-
}

do_compile_api() {
	cd ${S}/mpu_api/src
	oe_runmake PREFIX=${S} TGTROOT=${S} BUILD=rel CMDDEFS='GT_TRACE DEBUG' \
		CROSS=${AR%-*}-
	ln -s ${S}/mpu_api/src/bridge/libbridge.so.2 libbridge.so
}

do_compile() {
#	echo "BREAK0: ${STAGING_KERNEL_DIR}" && exit 1
	do_compile_driver
	do_compile_api
}

do_stage() {
	oe_libinstall -so -C ${S}/mpu_api/src/bridge libbridge ${STAGING_LIBDIR}
	oe_libinstall -a -C ${S}/mpu_api/src/qos libqos ${STAGING_LIBDIR}
	install -d ${STAGING_INCDIR}/dspbridge
	install -m 0644 ${S}/mpu_api/inc/*.h ${STAGING_INCDIR}/dspbridge/
}

do_install() {
	oe_libinstall -so -C ${S}/mpu_api/src/bridge libbridge ${D}/${libdir}
	oe_libinstall -a -C ${S}/mpu_api/src/qos libqos ${D}/${libdir}
}

FILES_${PN} += "${libdir}/libbridge.so.2 \
	${libdir}/libbridge.so \
	${libdir}/libqos.a"

addtask untar_packages after do_unpack before do_patch
