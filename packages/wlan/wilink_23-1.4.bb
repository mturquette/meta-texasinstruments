#SECTION = ""
DESCRIPTION = "WLAN stack (kernel module, libs, wpa_supplicant)"
PR = "r1"
# Note:	This recipe will be broken into many recipes for future releases
# 	For now this mimics the MCS layout and mini-build system in ClearCase

inherit ccasefetch

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "omap-3430ldp|omap-3430sdp|omap-zoom2-(alpha|beta)"

CCASE_SPEC = "%\
	element /vobs/WiLink/... LINUX-WCG-WLAN_RLS_${PV}-P1 %\
	element /vobs/WCGDev/... LINUX-WCG-WLAN_RLS_${PV}-P1 %\
	element /vobs/MCP_3P_OpenWPA/... LINUX-WCG-WLAN_RLS_${PV}-P1 %\
	"
	
#	element /vobs/WiLink/...	LINUX-WCG-WLAN_RLS_${PV} %\
#	element * /main/LATEST %\
#	"

CCASE_PATHFETCH = "/vobs/WiLink/ \
	/vobs/WCGDev \
	/vobs/MCP_3P_OpenWPA \
	"
CCASE_PATHCOMPONENTS = 0
CCASE_PATHCOMPONENT = "vobs"

SRC_URI = "file://fix-libestadrv-makefile.diff;patch=1 \
	file://fix-libuadrv-makefile.diff;patch=1 \
	file://fix-tiwlan_drv_stub-makefile.diff;patch=1 \
	file://fix-CUDK-makefile.diff;patch=1 \
	"

PACKAGES = "${PN}"
FILES_${PN} += "/wlan"

# nasty hack for the moment.  Will fix this in upstream makefiles for L23.13.
do_configure () {
	sed -i s#/vobs#${S}# \
		${S}/WiLink/external_drivers/omap3430/Linux/sdio/Makefile
}

do_compile () {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	cd ${S}/WiLink/platforms/os/linux
	make CROSS_COMPILE=${AR%-*}- ARCH=arm HOST_PLATFORM=omap3430 KERNEL_DIR=${STAGING_KERNEL_DIR} clean
	make CROSS_COMPILE=${AR%-*}- ARCH=arm HOST_PLATFORM=omap3430 KERNEL_DIR=${STAGING_KERNEL_DIR}
}

do_install() {
	install -d ${D}/wlan
	install -m 755 ${S}/WiLink/platforms/os/linux/wlan_cu ${D}/wlan
	install -m 755 ${S}/WiLink/platforms/os/linux/tiwlan.ini ${D}/wlan
	install -m 755 ${S}/WiLink/platforms/os/linux/tiwlan_loader ${D}/wlan
	install -m 755 ${S}/WiLink/platforms/os/linux/wpa_supplicant ${D}/wlan

	install -m 644 ${S}/WiLink/platforms/os/linux/tiwlan_drv.ko ${D}/wlan
	install -m 644 ${S}/WiLink/platforms/os/linux/firmware.bin ${D}/wlan
	install -m 644 ${S}/WiLink/platforms/os/linux/wpa_supplicant.txt ${D}/wlan

	install -m 644 ${S}/WiLink/external_drivers/omap3430/Linux/sdio/sdio.ko\
		${D}/wlan
}
