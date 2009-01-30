require linux-omap.inc
inherit ccasefetch

PR = "r1"

S = ${WORKDIR}/${PN}-${PV}/wtbu/CSSD_L_GIT_2.6/linux/kernel_org/2.6_kernel

CCASE_SPEC = "\
	element /vobs/wtbu/CSSD_L_GIT_2.6/linux/kernel_org/2.6_kernel/... LINUX-WCG-WLAN_RLS_23-1.1%\
	element /vobs/WiLink/... LINUX-WCG-WLAN_RLS_23-1.1%\
	element /vobs/WCGDev/... LINUX-WCG-WLAN_RLS_23-1.1%\
	element /vobs/wtbu/CSSD_L_GIT_2.6/linux/kernel_org/2.6_kernel/... LINUX-GIT-2.6.24K_RLS_5.19%\
	element * /main/LATEST%\
	"

CCASE_PATHFETCH = "/vobs/wtbu/CSSD_L_GIT_2.6/linux/kernel_org/2.6_kernel /vobs/WiLink/"
CCASE_PATHCOMPONENTS = 0
CCASE_PATHCOMPONENT = "vobs"

# You can supply your own defconfig if you like.  See
# http://bec-systems.com/oe/html/recipes_sources.html for a full explanation
#SRC_URI_omap-3430ldp = "file://defconfig-omap-3430ldp"
#SRC_URI_omap-3430sdp = "file://defconfig-omap-3430sdp"

do_stage_append() {
        install -d ${STAGING_KERNEL_DIR}/drivers/media/video/isp
        install -m 0644 ${S}/drivers/media/video/isp/*.h ${STAGING_KERNEL_DIR}/drivers/media/video/isp
}
