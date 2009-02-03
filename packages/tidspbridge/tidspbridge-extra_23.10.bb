PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU/DSP Bridge libraries."
LICENSE = "LGPL"
PR = "r1"
DEPENDS = "tidspbridge-module"

PACKAGES = "${PN}"

SRC_URI = "http://omapssp.dal.design.ti.com/VOBS/CSSD_Linux_Releases/3430/Linux_23.x/CSSD_Linux_${PV}RC4.tar.bz2 \
	file://bridge.init \
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
	install -m 0755 ${FILESDIR}/bridge.init ${D}${sysconfdir}/init.d/bridge
        # setup defaults:
        install -d ${D}/etc/default
        cat > ${D}/etc/default/bridge <<EOF
#
# Defaults for /etc/init.d/bridge
#
DEFAULT_BASEIMAGE=/lib/dsp/baseimage.dof

# for OMXResourceManager (also started by /etc/init.d/bridge):
export QOSDYN_FILE=/dspbridge/qosdyn_3430.dll64P

# for OMXPolicyManager (also started by /etc/init.d/bridge):
export PM_TBLFILE=/omx/policytable.tbl

# for OMXAudioManager (also started by /etc/init.d/bridge):
export DCTN_DLLFILE=/lib/dsp/dctn_dyn.dll64P

EOF

}

FILES_${PN} = "/dspbridge ${sysconfdir}/init.d/bridge ${sysconfdir}/default/bridge "
