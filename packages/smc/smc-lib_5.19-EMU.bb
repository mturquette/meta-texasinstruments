DESCRIPTION = "SMC libraries and init script"
SECTION = "libs"
PRIORITY = "optional"
RDEPENDS = "smc-module"
PR = "r0"

inherit ccasefetch

COMPATIBLE_MACHINE = "omap-3430(l|s)dp"

CCASE_SPEC =   "%\
	element /vobs/wtbu/OMAPSW_L/linux/applications/... LINUX-GIT-2.6.24K_RLS_${PV} %\
	element /vobs/wtbu/OMAPSW_L/linux/... COMPONENT_ROOT %\
	element * /main/LATEST %\
	"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_L/linux/applications/security/integration"
CCASE_PATHCOMPONENT = "integration"
CCASE_PATHCOMPONENTS = "6"

INITSCRIPT_NAME = "smc"
INITSCRIPT_PARAMS = "start 01 S ."

do_install() {
	install -d ${D}/${libdir}
	oe_libinstall -so -C ${S}/smc_omap3_normal_world_integration_kit/linux2_6_eabi/cryptoki/build/release libcryptoki ${D}${libdir}
	oe_libinstall -so -C ${S}/smc_omap3_normal_world_integration_kit/linux2_6_eabi/tzapi/build/release libsmapi ${D}${libdir}
	oe_libinstall -so -C ${S}/smc_omap3_normal_world_integration_kit/linux2_6_eabi/sst/build/release libsst ${D}${libdir}

	install -d ${D}${libdir}/smc
	install -m 644 ${S}/smc_omap3_secure_world_integration_kit/bin/smc_core_pa.ift ${D}${libdir}/smc/smc_pa.ift
	install -m 644 ${S}/smc_omap3_normal_world_integration_kit/linux2_6_eabi/OS_integration_components/smc_linux_cfg.ini ${D}${libdir}/smc
	install -m 755 ${S}/smc_omap3_normal_world_integration_kit/linux2_6_eabi/OS_integration_components/smc_pa_ctrl/build/release/smc_pa_ctrl ${D}${libdir}/smc
	install -m 755 ${S}/smc_omap3_normal_world_integration_kit/linux2_6_eabi/OS_integration_components/daemon/build/release/smoduled ${D}${libdir}/smc

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${FILESDIR}/init ${D}${sysconfdir}/init.d/smc
}

FILES_${PN} = "\
	/usr/lib \
	/usr/lib/smc \
	/etc/init.d/smc \
	"
