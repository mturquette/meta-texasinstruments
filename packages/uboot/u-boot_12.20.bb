require u-boot.inc

PR="r1"

SRC_URI = "file:///home/ddiaz/downs/CSSD_Linux_${PV}-u-boot.tar.gz \
	file://12.20-u-boot-armv7a.patch;patch=1"
S = "${WORKDIR}/CSSD_Linux_${PV}/src/u-boot"

UBOOT_MACHINE_omap-3430ldp = "omap3430labrador_config"
UBOOT_MACHINE_omap-3430sdp = "omap3430sdp_config"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "omap-3430(l|s)dp"
