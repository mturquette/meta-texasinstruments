SECTION = "kernel/modules"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU/DSP Bridge module."
LICENSE = "GPL"
PR = "r2"
COMPATIBLE_MACHINE = "omap-3430(l|s)dp"

RPROVIDES += "virtual/dspbridge-driver"
PROVIDES += "virtual/dspbridge-driver"

inherit module

SRC_URI = "http://omapssp.dal.design.ti.com/VOBS/CSSD_Linux_Releases/3430/Linux_12.x/CSSD_Linux_${PV}RC1.tar.bz2 \
	file://mkcross-driver.patch;patch=1"
S = ${WORKDIR}/CSSD_Linux_${PV}/src/bridge/mpu

do_unpack() {
	cd ${WORKDIR}
	tar jxf ${DL_DIR}/CSSD_Linux_${PV}RC1.tar.bz2 CSSD_Linux_${PV}/src/bridge/mpu/mpu_driver
}

do_compile() {
	cd ${S}/mpu_driver/src
	oe_runmake PREFIX=${S} PROJROOT=${S}/mpu_driver \
		KRNLSRC=${STAGING_KERNEL_DIR} \
		TGTROOT=${S} BUILD=rel CMDDEFS='GT_TRACE DEBUG' \
		CROSS=${AR%-*}-
}

do_install() {
	cd ${S}/mpu_driver/src
	install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/dspbridge
	install -m 0644 *${KERNEL_OBJECT_SUFFIX} ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/dspbridge
}
