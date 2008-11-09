PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU/DSP Bridge libraries."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "tidspbridge-module"

PACKAGES = "${PN}"

SRC_URI = "http://omapssp.dal.design.ti.com/VOBS/CSSD_Linux_Releases/3430/Linux_12.x/CSSD_Linux_${PV}RC1.tar.bz2 \
	file://bridge.init \
	"

S = ${WORKDIR}/target

do_unpack() {
	cd ${WORKDIR}
	tar jxf ${DL_DIR}/CSSD_Linux_${PV}RC1.tar.bz2 CSSD_Linux_${PV}/target/dspbridg --strip-components 1
}

do_install() {
	install -d ${D}/dspbridge
	install -m 0644 ${S}/dspbridge/*.*64P ${D}/dspbridge
	install -m 0755 ${S}/dspbridge/{install_bridge,uninstall_bridge,ping.out,cexec.out} ${D}/dspbridge
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${FILESDIR}/bridge.init ${D}${sysconfdir}/init.d/bridge
}

FILES_${PN} = "/dspbridge ${sysconfdir}/init.d/bridge "
