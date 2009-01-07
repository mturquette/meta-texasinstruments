DESCRIPTION = "SMC driver"
SECTION = "kernel/modules"
PRIORITY = "optional"
DEPENDS = "virtual/kernel"
PR = "r0"

inherit ccasefetch module

COMPATIBLE_MACHINE = "omap-3430(l|s)dp"
PACKAGE_ARCH = "${MACHINE_ARCH}"

EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX} LINUX_KERNEL_DIR=${STAGING_KERNEL_DIR} BUILD_VARIANT=release"
CCASE_SPEC =   "%\
	element /vobs/wtbu/OMAPSW_L/linux/applications/... LINUX-GIT-2.6.24K_RLS_${PV} %\
	element /vobs/wtbu/OMAPSW_L/linux/... COMPONENT_ROOT %\
	element * /main/LATEST %\
	"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_L/linux/applications/security/driver"
CCASE_PATHCOMPONENT = "driver"
CCASE_PATHCOMPONENTS = "6"

do_compile() {
	cd ${S}/build
        unset LDFLAGS
        unset CFLAGS
        unset CPPFLAGS
        oe_runmake
}

do_install() {
	install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/smc
        install -m 0644 ${S}/build/release/smc_driver.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/smc
}
