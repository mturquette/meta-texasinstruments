DESCRIPTION = "SMC drivers and installation"
SECTION = "libs"
PRIORITY = "optional"
RDEPENDS = "expat "
PR = "r2"

inherit ccasefetch

COMPATIBLE_MACHINE = "omap-3430(l|s)dp"
PACKAGE_ARCH = "${MACHINE_ARCH}"

EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX} LINUX_KERNEL_DIR=${STAGING_KERNEL_DIR} BUILD_VARIANT=release"
CCASE_SPEC =   "%\
	element /vobs/wtbu/OMAPSW_L/linux/applications/... LINUX-GIT-2.6.24K_RLS_${PV} %\
	element /vobs/wtbu/OMAPSW_L/linux/... COMPONENT_ROOT %\
	element * /main/LATEST %\
	"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_L/linux"
CCASE_PATHCOMPONENT = "applications"
CCASE_PATHCOMPONENTS = "4"

do_compile() {
        cd ${S}/security/driver/build/
        unset LDFLAGS
        unset CFLAGS
        unset CPPFLAGS
        oe_runmake -C ${S}/security/driver/build
}

do_install() {
	echo "TARGET_PREFIX is ${TARGET_PREFIX}"
	echo "CROSS is ${CROSS}"
        echo "PROJ_ROOT is : ${PROJ_ROOT}"
        echo "CROSS can be set to ${AR%-*}-"
        echo "KRNLSRC is ${KRNLSRC}"
        echo "STAGING_LIBDIR is ${STAGING_LIBDIR}"
        echo "STAGING_DIR is ${STAGING_DIR}"
        echo "libdir is ${libdir}"
        echo "COMPONENT_LIB is ${COMPONENT_LIB}"
        echo "S is  ${S}"
        echo "D is  ${D}"
        cd ${S}/security/driver/build/
        install -m 755 ${S}/security/integration/smc_omap3_normal_world_integration_kit/linux2_6_eabi/cryptoki/build/release/libcryptoki.so ${STAGING_LIBDIR}
        install -m 755 ${S}/security/integration/smc_omap3_normal_world_integration_kit/linux2_6_eabi/tzapi/build/release/libsmapi.so ${STAGING_LIBDIR}
        install -m 755 ${S}/security/integration/smc_omap3_normal_world_integration_kit/linux2_6_eabi/sst/build/release/libsst.so ${STAGING_LIBDIR}

	install -d ${STAGING_LIBDIR}/smc
        install -m 755 ${S}/security/driver/build/release/smc_driver.ko ${STAGING_LIBDIR}/smc
        install -m 755 ${S}/security/integration/smc_omap3_secure_world_integration_kit/bin/smc_core_pa.ift ${STAGING_LIBDIR}/smc/smc_pa.ift
        install -m 755 ${S}/security/integration/smc_omap3_normal_world_integration_kit/linux2_6_eabi/OS_integration_components/smc_linux_cfg.ini ${STAGING_LIBDIR}/smc
        install -m 755 ${S}/security/integration/smc_omap3_normal_world_integration_kit/linux2_6_eabi/OS_integration_components/smc_pa_ctrl/build/release/smc_pa_ctrl ${STAGING_LIBDIR}/smc
        install -m 755 ${S}/security/integration/smc_omap3_normal_world_integration_kit/linux2_6_eabi/OS_integration_components/daemon/build/release/smoduled ${STAGING_LIBDIR}/smc
}

pkg_postinst_smc () {
        #!/bin/sh -e
        # now make changes to rcS for boot time args
	if [ ! -e "$(S)/rcS_file_appended" ]; then
	   cat ${S}/rcS_file_append_text >> ${WORKDIR}/../../../rootfs/etc/init.d/rcS
           touch ${S}/rcS_file_appended
        fi
}


PACKAGES = "${PN}"
FILES_${PN} = "${STAGING_LIBDIR}/smc"
