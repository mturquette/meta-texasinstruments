PROVIDES="virtual/dspbridge-module"
RPROVIDES="virtual/dspbridge-module"
require linux-omap2.inc

FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/linux-omap3-git/${MACHINE}"


PR = "r30"

SRC_URI = "git://github.com/felipec/linux-omap.git;branch=tidspbridge;protocol=git file://defconfig"
SRCREV = "2c1b5e572b216c1682127a7b69c81493a42ebcab"
PV = "2.6.28-omap1+${PR}+git${SRCREV}"

#PV = "2.6.29-rc3+${PR}+git${SRCREV}"
#SRCREV = "350e3d45eada319c41cd716676e72d5284877c59"
#SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/tmlind/linux-omap-2.6.git;branch=tidspbridge;protocol=git \
#	   file://defconfig"

SRC_URI_append_beagleboard = " \
           file://no-empty-flash-warnings.patch;patch=1 \
           file://bridgenopm.patch;patch=1 \
          "

SRC_URI_append_omap3evm = " \
           file://no-empty-flash-warnings.patch;patch=1 \
          "

SRC_URI_append_overo = " \
           file://logo_linux_clut224.ppm \
           file://no-empty-flash-warnings.patch;patch=1 \
           file://overo.patch;patch=1 \
          "

COMPATIBLE_MACHINE = "beagleboard|omap3evm|overo"

S = "${WORKDIR}/git"
