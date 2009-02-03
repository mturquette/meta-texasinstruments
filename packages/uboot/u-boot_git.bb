require u-boot.inc

DEFAULT_PREFERENCE = "1"

SRC_URI = "git://git.omapzoom.org/repo/u-boot.git;branch=master;protocol=http \
	file://armv7a.patch;patch=1 \
	"

SRCREV = "0b3409c0ee357357338272a20975eaaf1fcef8a7"

COMPATIBLE_MACHINE = "omap-3430(l|s)dp|omap-zoom2-(alpha|beta)"

UBOOT_MACHINE_omap-3430ldp =	"omap3430labrador_config"
UBOOT_MACHINE_omap-3430sdp =	"omap3430sdp_config"
UBOOT_MACHINE_omap-zoom2-alpha ="omap3430zoom2_config"
UBOOT_MACHINE_omap-zoom2-beta =	"omap3430zoom2_config"

PR="r0"
PV="1.1.4+${PR}+git${SRCREV}"

S=${WORKDIR}/git
PACKAGE_ARCH = "${MACHINE_ARCH}"

do_stage() {
	install -d ${STAGING_BINDIR_NATIVE}
	install -m 755 tools/mkimage ${STAGING_BINDIR_NATIVE}/

	install -d ${STAGING_INCDIR}/${PN} \
		${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3 \
		${STAGING_INCDIR}/${PN}/linux \
		${STAGING_INCDIR}/${PN}/linux/byteorder

	install -m 644 ${S}/include/command.h ${STAGING_INCDIR}/${PN}/
	install -m 644 ${S}/include/fat.h ${STAGING_INCDIR}/${PN}/
	install -m 644 ${S}/include/ide.h ${STAGING_INCDIR}/${PN}/
	install -m 644 ${S}/include/malloc.h ${STAGING_INCDIR}/${PN}/
	install -m 644 ${S}/include/mmc.h ${STAGING_INCDIR}/${PN}/
	install -m 644 ${S}/include/part.h ${STAGING_INCDIR}/${PN}/

	install -m 644 ${S}/include/asm-arm/byteorder.h \
		${STAGING_INCDIR}/${PN}/asm-arm/

	install -m 644 ${S}/include/asm-arm/arch-omap3/bits.h  \
		${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3/
	install -m 644 ${S}/include/asm-arm/arch-omap3/clocks343x.h \
		${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3/
	install -m 644 ${S}/include/asm-arm/arch-omap3/clocks.h \
		${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3/
	install -m 644 ${S}/include/asm-arm/arch-omap3/cpu.h \
		${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3/
	install -m 644 ${S}/include/asm-arm/arch-omap3/mem.h \
		${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3/
	install -m 644 ${S}/include/asm-arm/arch-omap3/mux.h \
		${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3/
	install -m 644 ${S}/include/asm-arm/arch-omap3/omap3430.h \
		${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3/
	install -m 644 ${S}/include/asm-arm/arch-omap3/sizes.h \
		${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3/
	install -m 644 ${S}/include/asm-arm/arch-omap3/sys_info.h \
		${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3/
	install -m 644 ${S}/include/asm-arm/arch-omap3/sys_proto.h \
		${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3/

	install -m 644 ${S}/include/linux/byteorder/* \
		${STAGING_INCDIR}/${PN}/linux/byteorder/
	install -m 644 ${S}/include/linux/stat.h ${STAGING_INCDIR}/${PN}/linux/
	install -m 644 ${S}/include/linux/time.h ${STAGING_INCDIR}/${PN}/linux/

#	[ -s ${STAGING_INCDIR}/${PN}/asm ] ||
#		ln -sf asm-arm ${STAGING_INCDIR}/${PN}/asm

#	[ -s ${STAGING_INCDIR}/${PN}/asm-arm/arch ] ||
#		ln -sf arch-omap3 ${STAGING_INCDIR}/${PN}/asm-arm/arch
}
