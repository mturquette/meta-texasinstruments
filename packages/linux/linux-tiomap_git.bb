require linux-omap.inc

DEFAULT_PREFERENCE = "1"

FILESDIR = "${FILE_DIRNAME}/${PN}-git/${MACHINE}"

# stable-2.6.27.8 tag
SRCREV = "d74c8c68c38408e0cfc39ca9fbfe84ab8db3ad93"

PV = "2.6.27.8+${PR}+git${SRCREV}"
PR = "r1"

SRC_URI = "git://git.omapzoom.org/repo/omapkernel.git;branch=master;protocol=http"

SRC_URI_append_omap-zoom2-beta = " \
           file://zoom2-beta-disp-params.diff;patch=1 \
          "

S = "${WORKDIR}/git"
