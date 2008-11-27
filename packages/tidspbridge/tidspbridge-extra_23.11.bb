DESCRIPTION = "Texas Instruments MPU/DSP Bridge libraries."
PR = "r0"
DEPENDS = "tidspbridge-module"

PACKAGES = "${PN}"

SRC_URI = "\
	file://23.10-bridge.init \
	file://install_bridge \
	file://uninstall_bridge \
	file://cexec.out \
	"

do_install() {
	install -d ${D}/dspbridge
	install -m 0755 ${FILESDIR}/{install_bridge,uninstall_bridge,cexec.out} ${D}/dspbridge
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${FILESDIR}/23.10-bridge.init ${D}${sysconfdir}/init.d/bridge
}

FILES_${PN} = "/dspbridge ${sysconfdir}/init.d/bridge "

pkg_postinst_${PN} () {
	if [ x"$D" = "x" ]; then
		ln -sf /lib/modules/`uname -r`/kernel/drivers/dspbridge/bridgedriver.ko /dspbridge/bridgedriver.ko
	fi
}
