PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU/DSP Bridge libraries."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "tidspbridge-module"

PACKAGES = "${PN}"

SRC_URI = "http://omapssp.dal.design.ti.com/VOBS/CSSD_Linux_Releases/3430/Linux_23.x/CSSD_Linux_${PV}RC4.tar.bz2 \
	file://23.10-bridge.init \
	"

S = ${WORKDIR}/target

do_unpack() {
	cd ${WORKDIR}
	tar jxf ${DL_DIR}/CSSD_Linux_${PV}RC4.tar.bz2 CSSD_Linux_${PV}RC4/target/dspbridge --strip-components 1
}

do_install() {
	install -d ${D}/dspbridge
	install -m 0644 ${S}/dspbridge/*.*64P ${D}/dspbridge
	install -m 0755 ${S}/dspbridge/{install_bridge,uninstall_bridge,ping.out,cexec.out} ${D}/dspbridge
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${FILESDIR}/23.10-bridge.init ${D}${sysconfdir}/init.d/bridge
}

FILES_${PN} = "/dspbridge ${sysconfdir}/init.d/bridge "

pkg_postinst_${PN} () {
	if [ x"$D" = "x" ]; then
		ln -sf /lib/modules/`uname -r`/kernel/drivers/dspbridge/bridgedriver.ko /dspbridge/bridgedriver.ko
	fi
}
