PR = "r1"

inherit ccasefetch

COMPATIBLE_MACHINE = "omap-3430ldp|omap-3430sdp"
DEFAULT_PREFERENCE = "1"

CCASE_SPEC = "%\
	element /vobs/WiLink/...	LINUX-WCG-WLAN_RLS_${PV} %\
	element * /main/LATEST %\
	"

CCASE_PATHFETCH = "/vobs/WiLink/platforms/os/linux/"
CCASE_PATHCOMPONENTS = 1
CCASE_PATHCOMPONENT = "WiLink"

PACKAGES = "${PN}"
FILES_${PN} += "/wlan"

do_install() {
        cd ${S}/platforms/os/linux/
        make
	install -d ${D}/wlan
        tar -xvf ${S}/platforms/os/linux/omap3430Binaries.tar
	install -m 0644 ${S}/platforms/os/linux/sdio.ko ${D}/wlan
	install -m 0644 ${S}/platforms/os/linux/tiwlan_drv.ko ${D}/wlan
	install -m 0644 ${S}/platforms/os/linux/tiwlan.ini ${D}/wlan
	install -m 0755 ${S}/platforms/os/linux/wlan_cu ${D}/wlan
	install -m 0755 ${S}/platforms/os/linux/tiwlan_loader ${D}/wlan
	
	install -m 0644 ${S}/platforms/os/linux/firmware.bin ${D}/wlan/firmware.bin
	
	install -m 0644 ${S}/platforms/os/linux/wpa_supplicant.txt ${D}/wlan

	install -m 0755 ${S}/platforms/os/linux/wpa_supplicant ${D}/wlan
}
