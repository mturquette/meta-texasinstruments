DESCRIPTION = "Texas Instruments MPU/DSP Bridge libraries."
PR = "r0"
DEPENDS = "virtual/dspbridge-module"

inherit update-rc.d

PACKAGES = "${PN}"

SRC_URI = "\
	file://bridge.init \
	file://cexec.out \
	"

INITSCRIPT_NAME = "bridge"
INITSCRIPT_PARAMS = "start 01 5 ."


do_install() {
	install -d ${D}/dspbridge
	install -m 0755 ${FILESDIR}/cexec.out ${D}/dspbridge
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${FILESDIR}/bridge.init ${D}${sysconfdir}/init.d/bridge
        # setup defaults:
        install -d ${D}/etc/default
        cat > ${D}/etc/default/bridge <<EOF
#
# Defaults for /etc/init.d/bridge
#
DEFAULT_BASEIMAGE=/lib/dsp/baseimage.dof
EOF

}

FILES_${PN} = "/dspbridge ${sysconfdir}/init.d/bridge ${sysconfdir}/default/bridge "
