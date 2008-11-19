DESCRIPTION = "Bluetooth and FM modules for OMAP"
PRIORITY = "optional"
RDEPENDS = "expat dbus bluez-libs bluez-utils openobex obexftp"
LICENSE = "LGPL"
PR = "r0"

COMPATIBLE_MACHINE = "omap-3430ldp|omap-3430sdp"

SRC_URI = "file://L23.10_BTFM.tar.bz2"

S = "${WORKDIR}/btfm/linux"

do_install() {
	install -d ${D}/lib/firmware
	install -t ${D}/lib/firmware ${WORKDIR}/btfm/linux/init_scripts/*
}

FILES_${PN} = "/lib/firmware"
