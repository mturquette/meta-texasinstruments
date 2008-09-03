SECTION = "kernel/modules"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU/DSP Bridge module."
LICENSE = "GPL"
PR = "r2"
COMPATIBLE_MACHINE = "omap-3430ldp"

inherit module

SRC_URI = "http://omapssp.dal.design.ti.com/VOBS/CSSD_Linux_Releases/3430/Linux_12.x/CSSD_Linux_${PV}RC1.tar.bz2 \
	file://mkcross-driver.patch;patch=1"
#S = ${WORKDIR}/CSSD_Linux_12.20/src/bridge/mpu
S = ${WORKDIR}/CSSD_Linux_${PV}/src/bridge/mpu

#		KRNLSRC=${STAGING_DIR}/omap-3430ldp-poky-linux-gnueabi/kernel \

do_compile() {
	cd ${S}/mpu_driver/src
	oe_runmake PREFIX=${S} PROJROOT=${S}/mpu_driver \
		KRNLSRC=${STAGING_KERNEL_DIR} \
		TGTROOT=${S} BUILD=rel CMDDEFS='GT_TRACE DEBUG' \
		CROSS=${AR%-*}-
}

do_install() {
	cd ${S}/mpu_driver/src
	oe_runmake TARGETDIR=${D} PREFIX=${S} PROJROOT=${S}/mpu_driver \
		KRNLSRC=${STAGING_KERNEL_DIR} \
		TGTROOT=${S} BUILD=relinstall 
#	oe_runmake DEPMOD=echo INSTALL_MOD_PATH="${D}" CC="${KERNEL_CC}" LD="${KERNEL_LD}" modules_install
}
