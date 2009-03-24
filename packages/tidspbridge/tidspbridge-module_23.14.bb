SECTION = "kernel/modules"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU/DSP Bridge module."
LICENSE = "GPL"
PR = "r0"
COMPATIBLE_MACHINE = "omap-3430(l|s)dp"

# We need to override this and make sure it's only -j1
PARALLEL_MAKE = "-j1"

inherit module ccasefetch

SRC_URI = "file://23.12-mkcross-driver.patch;patch=1"

#SRC_URI = "\
#	file://23.12-mpu-driver-armv7a.patch;patch=1 \
#	file://23.12-mpu-driver-mkcross.patch;patch=1 \
#	"

CCASE_SPEC = "%\
	element * COMPONENT_ROOT%\
	element /vobs/wtbu/OMAPSW_MPU/dspbridge/... L-BRIDGE-MPU_RLS_${PV}%\
	element * /main/LATEST%\
	"
CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_MPU/dspbridge"
CCASE_PATHCOMPONENT = "dspbridge"
CCASE_PATHCOMPONENTS = "3"

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
