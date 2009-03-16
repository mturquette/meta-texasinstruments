PROVIDES="virtual/dspbridge-module"
RPROVIDES="virtual/dspbridge-module"

require linux-omap.inc

PR = "r0"
DEFAULT_PREFERENCE = "1"

FILESDIR = "${FILE_DIRNAME}/${PN}-git/${MACHINE}"

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/felipec/linux-omap.git;branch=tidspbridge-stable;protocol=git"
SRCREV = "1764d8eef033d40c623e9a9a0dde455e59ac2149"
PV = "2.6.28-omap1+${PR}+git${SRCREV}"

SRC_URI_append_beagleboard = " \
	file://defconfig-beagleboard \
	"
