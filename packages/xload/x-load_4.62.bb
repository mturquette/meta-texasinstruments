SECTION = "bootloaders"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments X-Loader boot utility"
LICENSE = "GPL"
PR="r1"
DEPENDS="u-boot"

inherit ccasefetch

CCASE_SPEC = "%\
	element /vobs/wtbu/OMAPSW_L/x-load/...  X-LOAD_RLS_${PV} %\
	element /vobs/wtbu/OMAPSW_L /main/LATEST %\
	element * /main/0 %\
	"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_L/x-load"
CCASE_PATHCOMPONENT = "x-load"
CCASE_PATHCOMPONENTS = "3"

XLOAD_MACHINE_omap-3430ldp = "omap3430labrador_config"
XLOAD_MACHINE_omap-3430sdp = "omap3430sdp_config"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "omap-3430(l|s)dp"

#EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX} -I${STAGING_INCDIR}/u-boot/"
EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX}"
PARALLEL_MAKE = ""

XLOAD_IMAGE ?= "${PN}-${MACHINE}-${PV}-${PR}.bin"
XLOAD_SYMLINK ?= "${PN}-${MACHINE}.bin"

do_configure() {
	cd ${S}/include
	ln -sf ${STAGING_INCDIR}/u-boot/command.h
	ln -sf ${STAGING_INCDIR}/u-boot/fat.h
	ln -sf ${STAGING_INCDIR}/u-boot/ide.h
	ln -sf ${STAGING_INCDIR}/u-boot/malloc.h
	ln -sf ${STAGING_INCDIR}/u-boot/mmc.h
	ln -sf ${STAGING_INCDIR}/u-boot/part.h

	cd ${S}/include/asm
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/byteorder.h

	cd ${S}/include/asm/arch-omap3
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/arch-omap3/bits.h
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/arch-omap3/clocks.h
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/arch-omap3/clocks343x.h
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/arch-omap3/cpu.h
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/arch-omap3/mem.h
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/arch-omap3/mux.h
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/arch-omap3/omap3430.h
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/arch-omap3/sizes.h
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/arch-omap3/sys_info.h
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/arch-omap3/sys_proto.h

	cd ${S}/include/linux
	ln -sf ${STAGING_INCDIR}/u-boot/linux/stat.h
	ln -sf ${STAGING_INCDIR}/u-boot/linux/time.h
	ln -sf ${STAGING_INCDIR}/u-boot/linux/byteorder
}

do_compile () {
	unset LDFLAGS
	unset CFLAGS
	unset CPPFLAGS
	oe_runmake ${XLOAD_MACHINE}
	oe_runmake all
}

do_deploy () {
	install -d ${DEPLOY_DIR_IMAGE}
	install ${S}/x-load.bin ${DEPLOY_DIR_IMAGE}/${XLOAD_IMAGE}
	package_stagefile_shell ${DEPLOY_DIR_IMAGE}/${XLOAD_IMAGE}

	cd ${DEPLOY_DIR_IMAGE}
	rm -f ${XLOAD_SYMLINK}
	ln -sf ${XLOAD_IMAGE} ${XLOAD_SYMLINK}
	package_stagefile_shell ${DEPLOY_DIR_IMAGE}/${XLOAD_SYMLINK}
}

do_deploy[dirs] = "${S}"
addtask deploy before do_build after do_compile
