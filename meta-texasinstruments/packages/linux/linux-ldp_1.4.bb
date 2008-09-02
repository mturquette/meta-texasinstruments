SECTION = "kernel"
DESCRIPTION = "Linux kernel for TI OMAP(tm) 3430 LDP devices"
LICENSE = "GPL"
PR = "r1"
KERNEL_OUTPUT = "arch/${ARCH}/boot/compressed/${KERNEL_IMAGETYPE}"
SRC_URI="http://omapzoom.org/gf/download/frsrelease/110/425/linux-ldp-v${PV}.tar"
S = "${WORKDIR}/2.6_kernel"
COMPATIBLE_MACHINE = "omap-3430ldp"

inherit kernel

do_configure() {
	yes '' | oe_runmake omap_3430ldp_defconfig
}

do_deploy() {
	install -d ${DEPLOY_DIR_IMAGE}
	install -m 0644 arch/${ARCH}/boot/${KERNEL_IMAGETYPE} ${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE}-${PV}-${MACHINE}-${DATETIME}.bin
	cd ${DEPLOY_DIR_IMAGE}
	ln -sf ${KERNEL_IMAGETYPE}-${PV}-${MACHINE}-${DATETIME}.bin ${KERNEL_IMAGETYPE}-${MACHINE}.bin
	tar -cvzf ${DEPLOY_DIR_IMAGE}/modules-${KERNEL_RELEASE}-${MACHINE}.tgz -C ${D} lib
}

do_deploy[dirs] = "${S}"

addtask deploy before do_populate_staging after do_install
