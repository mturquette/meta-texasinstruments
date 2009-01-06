DESCRIPTION = "SMC drivers and installation"
SECTION = "libs"
PRIORITY = "optional"
DEPENDS = "virtual/kernel"
PR = "r3"

inherit ccasefetch

COMPATIBLE_MACHINE = "omap-3430(l|s)dp"
PACKAGE_ARCH = "${MACHINE_ARCH}"

EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX} LINUX_KERNEL_DIR=${STAGING_KERNEL_DIR} BUILD_VARIANT=release"
CCASE_SPEC =   "%\
	element /vobs/wtbu/OMAPSW_L/linux/applications/... LINUX-GIT-2.6.24K_RLS_${PV} %\
	element /vobs/wtbu/OMAPSW_L/linux/... COMPONENT_ROOT %\
	element * /main/LATEST %\
	"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_L/linux/applications/security/driver/ \
	/vobs/wtbu/OMAPSW_L/linux/applications/security/integration \
	"
CCASE_PATHCOMPONENT = "security"
CCASE_PATHCOMPONENTS = "5"

INITSCRIPT_NAME = "smc"
INITSCRIPT_PARAMS = "start 01 S ."

do_compile() {
	cd ${S}/driver/build
        unset LDFLAGS
        unset CFLAGS
        unset CPPFLAGS
        oe_runmake
}

do_install() {
	install -d ${D}/${libdir}
	oe_libinstall -so -C ${S}/integration/smc_omap3_normal_world_integration_kit/linux2_6_eabi/cryptoki/build/release libcryptoki ${D}${libdir}
	oe_libinstall -so -C ${S}/integration/smc_omap3_normal_world_integration_kit/linux2_6_eabi/tzapi/build/release libsmapi ${D}${libdir}
	oe_libinstall -so -C ${S}/integration/smc_omap3_normal_world_integration_kit/linux2_6_eabi/sst/build/release libsst ${D}${libdir}

        install -m 755 ${S}/integration/smc_omap3_normal_world_integration_kit/linux2_6_eabi/cryptoki/build/release/libcryptoki.so ${STAGING_LIBDIR}
        install -m 755 ${S}/integration/smc_omap3_normal_world_integration_kit/linux2_6_eabi/tzapi/build/release/libsmapi.so ${STAGING_LIBDIR}
        install -m 755 ${S}/integration/smc_omap3_normal_world_integration_kit/linux2_6_eabi/sst/build/release/libsst.so ${STAGING_LIBDIR}

	install -d ${D}${libdir}/smc
        install -m 644 ${S}/driver/build/release/smc_driver.ko ${D}${libdir}/smc
        install -m 644 ${S}/integration/smc_omap3_secure_world_integration_kit/bin/smc_core_pa.ift ${D}${libdir}/smc/smc_pa.ift
        install -m 644 ${S}/integration/smc_omap3_normal_world_integration_kit/linux2_6_eabi/OS_integration_components/smc_linux_cfg.ini ${D}${libdir}/smc
        install -m 755 ${S}/integration/smc_omap3_normal_world_integration_kit/linux2_6_eabi/OS_integration_components/smc_pa_ctrl/build/release/smc_pa_ctrl ${D}${libdir}/smc
        install -m 755 ${S}/integration/smc_omap3_normal_world_integration_kit/linux2_6_eabi/OS_integration_components/daemon/build/release/smoduled ${D}${libdir}/smc

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${FILESDIR}/init ${D}${sysconfdir}/init.d/smc
}
