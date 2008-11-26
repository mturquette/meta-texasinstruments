require linux-omap.inc
inherit ccasefetch

PR = "r0"
PV = "2.6.24.7"

COMPATIBLE_MACHINE = "omap-3430ldp|omap-3430sdp"
DEFAULT_PREFERENCE = "1"

CCASE_SPEC = "\
	element /vobs/wtbu/CSSD_L_GIT_2.6/linux/kernel_org/2.6_kernel/... LINUX-GIT-2.6.24K_RLS_5.19%\
	element * /main/LATEST%\
	"
CCASE_PATHFETCH = "/vobs/wtbu/CSSD_L_GIT_2.6/linux/kernel_org/2.6_kernel"
CCASE_PATHCOMPONENTS = 5
CCASE_PATHCOMPONENT = "2.6_kernel"

SRC_URI_omap-3430ldp = "file://defconfig-omap-3430ldp"
SRC_URI_omap-3430sdp = "file://defconfig-omap-3430sdp"
