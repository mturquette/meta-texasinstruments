DESCRIPTION = "Bluetooth and FM modules for OMAP"
SECTION = "libs"
PRIORITY = "optional"
RDEPENDS = "expat dbus bluez-libs bluez-utils openobex obexftp"
LICENSE = "LGPL"
PR = "r1"

COMPATIBLE_MACHINE = "omap-3430ldp|omap-3430sdp"

CCASE_SPEC =   "%\
		element /vobs/WCGDev/... LINUX-WCG-BT_RLS_${PV}%\
		element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/WCGDev"
CCASE_PATHCOMPONENT = "WCGDev"
CCASE_PATHCOMPONENTS = "2"

do_install() {
        chmod -R +w ${S}/*
	install -d ${D}${base_libdir}/firmware
	install -m 755 ${S}/linux/init_scripts/* ${D}${base_libdir}/firmware
}

PACKAGES = "${PN}"
FILES_${PN} = "${layout_base_libdir}/firmware"
