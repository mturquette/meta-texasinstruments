DESCRIPTION = "Bluetooth and FM modules for OMAP"
SECTION = "libs"
PRIORITY = "optional"
RDEPENDS = "expat dbus bluez-libs bluez-utils openobex obexftp"
LICENSE = "LGPL"
PR = "r0"

COMPATIBLE_MACHINE = "omap-3430ldp|omap-3430sdp"

SRC_URI = "file://L23.10_BTFM.tar.bz2"

S = "${WORKDIR}/btfm/linux"

do_install() {
	install -d ${D}/lib/firmware
	install -m 755 ${S}/init_scripts/* ${D}/lib/firmware
}

FILES_${PN} = "/lib/firmware"
