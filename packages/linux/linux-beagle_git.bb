PROVIDES="virtual/dspbridge-module"
RPROVIDES="virtual/dspbridge-module"

require linux-omap.inc

PR = "r0"
DEFAULT_PREFERENCE = "1"

FILESDIR = "${FILE_DIRNAME}/${PN}-git/${MACHINE}"

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/felipec/linux-omap.git;protocol=git"
SRCREV = "9f40e7784c723e23175c6c2c50256fd010e559c4"
PV = "2.6.28-felipec1+${PR}+git${SRCREV}"

#SRC_URI_append_beagleboard = " \
#	file://defconfig-beagleboard \
#	"
