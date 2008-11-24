PR = "r1"

COMPATIBLE_MACHINE = "omap-3430ldp|omap-3430sdp"
DEFAULT_PREFERENCE = "1"
#DEPENDS = "linux-tiomap"

SRC_URI=" \
	file://wilinkbins-20081112.tar.gz \
	"
do_install() {
	install -d ${D}/wlan
	install -m 0644 ${WORKDIR}/vobs/WiLink/platforms/os/linux/voice/tiwlan.ini ${D}/wlan
	install -m 0755 ${WORKDIR}/vobs/WiLink/platforms/os/linux/voice/wlan_cu ${D}/wlan
	install -m 0644 ${WORKDIR}/vobs/WiLink/platforms/os/linux/voice/wpa_supplicant.txt ${D}/wlan
	install -m 0755 ${WORKDIR}/vobs/WiLink/platforms/os/linux/apps/tiwlan_loader ${D}/wlan
	install -m 0755 ${WORKDIR}/vobs/WiLink/platforms/os/linux/suppl/wpa_supplicant ${D}/wlan
	install -m 0755 ${WORKDIR}/vobs/WiLink/external_apps/Linux/iperf-2.0.2/src/iperf ${D}/wlan
	install -m 0644 ${WORKDIR}/vobs/WiLink/fw/Latest/Fw1273_CHIP.bin ${D}/wlan/firmware.bin
}

FILES_${PN} += "\
	/wlan/tiwlan.ini \
	/wlan/wlan_cu \
	/wlan/wpa_supplicant.txt \
	/wlan/tiwlan_loader \
	/wlan/wpa_supplicant \
	/wlan/iperf \
	/wlan/wlan_cu \
	/wlan/firmware.bin \
	"
