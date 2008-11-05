require linux-omap.inc

PR = "r2"

COMPATIBLE_MACHINE = "omap-3430ldp|omap-3430sdp|beagleboard"
DEFAULT_PREFERENCE = "1"

SRC_URI=" \
	http://omapssp.dal.design.ti.com/VOBS/CSSD_Linux_Releases/3430/Linux_12.x/CSSD_Linux_12.20RC1.tar.bz2 \
	file://defconfig-omap-3430ldp \
	"

#SRC_URI_append_omap-3430ldp = "file://defconfig-omap-3430ldp"

S = "${WORKDIR}/CSSD_Linux_12.20/src/linux/kernel_org/2.6_kernel"

do_unpack() {
	cd ${WORKDIR}
	tar -xjf ${DL_DIR}/CSSD_Linux_12.20RC1.tar.bz2 CSSD_Linux_12.20/src/linux/kernel_org/2.6_kernel
}
