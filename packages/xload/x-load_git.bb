SECTION = "bootloaders"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments X-Loader boot utility"
LICENSE = "GPL"
PR="r0"
DEPENDS="u-boot support-tools-native"

DEFAULT_PREFERENCE = "1"

XLOAD_MACHINE_omap-3430ldp =	"omap3430labrador_config"
XLOAD_MACHINE_omap-3430sdp =	"omap3430sdp_config"
XLOAD_MACHINE_omap-zoom2-alpha ="omap3430zoom2_config"
XLOAD_MACHINE_omap-zoom2-beta =	"omap3430zoom2_config"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "omap-3430(l|s)dp|omap-zoom2-(alpha|beta)"

#EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX} -I${STAGING_INCDIR}/u-boot/"
EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX}"
PARALLEL_MAKE = ""

XLOAD_IMAGE ?= "${PN}-${MACHINE}-${PV}-${PR}-${DATETIME}.bin"
XLOAD_SYMLINK ?= "${PN}-${MACHINE}.bin"

XLOAD_MLO_IMAGE ?= "MLO-${MACHINE}-${PV}-${PR}-${DATETIME}"
XLOAD_MLO_SYMLINK ?= "MLO"

S = ${WORKDIR}/git

SRC_URI = "git://git.omapzoom.org/repo/x-loader.git;branch=master;protocol=http \
	file://armv7a.patch;patch=1 \
	"
SRCREV = "17480cef38c3ff4d8cce9518af110976714e468a"

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

	omap3430gp-signer.pl ${S}/x-load.bin MLO
}

do_deploy () {
	install -d ${DEPLOY_DIR_IMAGE}
	install ${S}/x-load.bin ${DEPLOY_DIR_IMAGE}/${XLOAD_IMAGE}
	package_stagefile_shell ${DEPLOY_DIR_IMAGE}/${XLOAD_IMAGE}
	install ${S}/MLO ${DEPLOY_DIR_IMAGE}/${XLOAD_MLO_IMAGE}
	package_stagefile_shell ${DEPLOY_DIR_IMAGE}/${XLOAD_IMAGE}

	cd ${DEPLOY_DIR_IMAGE}
	rm -f ${XLOAD_SYMLINK}
	ln -sf ${XLOAD_IMAGE} ${XLOAD_SYMLINK}
	package_stagefile_shell ${DEPLOY_DIR_IMAGE}/${XLOAD_SYMLINK}
	rm -f ${XLOAD_MLO_SYMLINK}
	ln -sf ${XLOAD_MLO_IMAGE} ${XLOAD_MLO_SYMLINK}
	package_stagefile_shell ${DEPLOY_DIR_IMAGE}/${XLOAD_SYMLINK}
}

do_deploy[dirs] = "${S}"
addtask deploy before do_build after do_compile
