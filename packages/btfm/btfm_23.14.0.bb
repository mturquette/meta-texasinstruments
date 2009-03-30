DESCRIPTION = "Bluetooth and FM modules for OMAP"
SECTION = "libs"
PRIORITY = "optional"
RDEPENDS = "expat dbus bluez-libs bluez-utils bluez-utils-alsa openobex obexftp"
PR = "r2"

inherit ccasefetch

COMPATIBLE_MACHINE = "omap-3430(l|s)dp"

CCASE_SPEC =   "%\
	element /vobs/WCGDev/... LINUX-WCG-BT_RLS_${PV} %\
	element * /main/LATEST %\
	"

CCASE_PATHFETCH = "/vobs/WCGDev/linux/init_scripts"
CCASE_PATHCOMPONENT = "init_scripts"
CCASE_PATHCOMPONENTS = "3"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 755 ${S}/TIInit_7.1.24.bts ${D}${base_libdir}/firmware
	install -m 755 ${S}/TIInit_7.1.24.bts.3000000 ${D}${base_libdir}/firmware
	install -m 755 ${S}/fm_rx_init_1273.1.bts ${D}${base_libdir}/firmware
	install -m 755 ${S}/fm_tx_init_1273.1.bts ${D}${base_libdir}/firmware
	install -m 755 ${S}/fmc_init_1273.1.bts ${D}${base_libdir}/firmware
       install -m 755 ${S}/TIInit_7.2.31.bts ${D}${base_libdir}/firmware
}

PACKAGES = "${PN}"
FILES_${PN} = "${layout_base_libdir}/firmware"
