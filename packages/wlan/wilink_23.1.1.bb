PR = "r1"

inherit ccasefetch

COMPATIBLE_MACHINE = "omap-3430ldp|omap-3430sdp"
DEFAULT_PREFERENCE = "1"

CCASE_SPEC = "%\
	element /vobs/WiLink/...	LINUX-WCG-WLAN_RLS_23-1.1 %\
	element * /main/LATEST %\
	"

CCASE_PATHFETCH = "/vobs/WiLink/platforms/os/linux/voice/ \
	/vobs/WiLink/platforms/os/linux/apps /vobs/WiLink/fw/Latest/"
CCASE_PATHCOMPONENTS = 1
CCASE_PATHCOMPONENT = "WiLink"

PACKAGES = "${PN}"
FILES_${PN} += "/wlan"

do_install() {
	install -d ${D}/wlan
	install -m 0644 ${S}/platforms/os/linux/voice/tiwlan.ini ${D}/wlan
	install -m 0755 ${S}/platforms/os/linux/voice/wlan_cu ${D}/wlan
	install -m 0755 ${S}/platforms/os/linux/apps/tiwlan_loader ${D}/wlan
	
	install -m 0644 ${S}/fw/Latest/Fw1273_CHIP.bin ${D}/wlan/firmware.bin
	
	install -m 0644 ${S}/platforms/os/linux/voice/wpa_supplicant.txt ${D}/wlan
}
