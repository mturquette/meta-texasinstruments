require u-boot.inc
inherit ccasefetch

PR="r0"

CCASE_SPEC = "%\
	element /vobs/wtbu/OMAPSW_L/u-boot/...  LINUX-U-BOOT-114_RLS_${PV} %\
	element /vobs/wtbu/OMAPSW_L /main/LATEST %\
	element * /main/0 %\
	"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_L/u-boot"
CCASE_PATHCOMPONENT = "u-boot"
CCASE_PATHCOMPONENTS = "3"

UBOOT_MACHINE_omap-3430ldp = "omap3430labrador_config"
UBOOT_MACHINE_omap-3430sdp = "omap3430sdp_config"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "omap-3430(l|s)dp"

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
}
