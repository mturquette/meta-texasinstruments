require linux-omap.inc
inherit ccasefetch

PR = "r2"

COMPATIBLE_MACHINE = "omap-3430ldp|omap-3430sdp"
DEFAULT_PREFERENCE = "1"

CCASE_SPEC = " \
	element /vobs/wtbu/CSSD_L_GIT_2.6/linux/kernel_org/2.6_kernel/... LINUX-GIT-2.6.24K_RLS_5.17 \
	element * /main/LATEST \
	"

CCASE_PATHFETCH = "/vobs/wtbu/CSSD_L_GIT_2.6/linux/kernel_org/2.6_kernel"
CCASE_PATHCOMPONENTS = 5
CCASE_PATHCOMPONENT = "2.6_kernel"

SRC_URI="file://defconfig-omap-3430ldp" 
