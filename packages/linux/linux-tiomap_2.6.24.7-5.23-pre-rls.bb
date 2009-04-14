require linux-omap.inc
inherit ccasefetch

PR = "r0"

COMPATIBLE_MACHINE = "omap-3430ldp|omap-3430sdp"
DEFAULT_PREFERENCE = "1"

CCASE_SPEC = "\
	element /vobs/wtbu/CSSD_L_GIT_2.6/linux/kernel_org/2.6_kernel/... ACT_GIT-PRE-RLS-23.15%\
	element	/vobs/wtbu/CSSD_L_GIT_2.6/linux/kernel_org	/main/LATEST%\
	element	/vobs/wtbu/CSSD_L_GIT_2.6/linux			/main/LATEST%\
	element	/vobs/wtbu/CSSD_L_GIT_2.6			/main/LATEST%\
	element *						/main/0%\
	"
CCASE_PATHFETCH = "/vobs/wtbu/CSSD_L_GIT_2.6/linux/kernel_org/2.6_kernel"
CCASE_PATHCOMPONENTS = 5
CCASE_PATHCOMPONENT = "2.6_kernel"

# You can supply your own defconfig if you like.  See
# http://bec-systems.com/oe/html/recipes_sources.html for a full explanation
#SRC_URI_omap-3430ldp += "file://defconfig-omap-3430ldp"
#SRC_URI_omap-3430sdp += "file://defconfig-omap-3430sdp"

do_stage_append() {
	install -d ${STAGING_KERNEL_DIR}/drivers/media/video/isp
	install -m 0644 ${S}/drivers/media/video/isp/*.h ${STAGING_KERNEL_DIR}/drivers/media/video/isp
	install -d ${STAGING_KERNEL_DIR}/arch/arm/mach-omap2
	install -m 0644 ${S}/arch/arm/mach-omap2/*.h ${STAGING_KERNEL_DIR}/arch/arm/mach-omap2
}
