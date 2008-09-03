require linux-omap.inc

PR = "r2"

COMPATIBLE_MACHINE = "omap-3430ldp"

SRC_URI="http://omapssp.dal.design.ti.com/VOBS/CSSD_Linux_Releases/3430/Linux_12.x/CSSD_Linux_${PV}RC1.tar.bz2"

S = "${WORKDIR}/CSSD_Linux_${PV}/src/linux/kernel_org/2.6_kernel \
         file://defconfig-omap-3430ldp"
