require u-boot.inc

PR="r0"

SRC_URI = "http://omapssp.dal.design.ti.com/VOBS/CSSD_Linux_Releases/3430/Linux_23.x/CSSD_Linux_${PV}RC4.tar.bz2 \
	file://12.20-u-boot-armv7a.patch;patch=1"
S = "${WORKDIR}/src/u-boot"

UBOOT_MACHINE_omap-3430ldp = "omap3430labrador_config"
UBOOT_MACHINE_omap-3430sdp = "omap3430sdp_config"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "omap-3430(l|s)dp"

do_unpack() {
	cd ${WORKDIR}
	tar jxf ${DL_DIR}/CSSD_Linux_${PV}RC4.tar.bz2 CSSD_Linux_${PV}RC4/src/u-boot --strip-components 1
}
