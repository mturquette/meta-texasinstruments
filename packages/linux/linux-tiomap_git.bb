require linux-omap.inc

DEFAULT_PREFERENCE = "1"

FILESDIR = "${FILE_DIRNAME}/${PN}-git/${MACHINE}"

RPROVIDES += "virtual/dspbridge-driver"
PROVIDES += "virtual/dspbridge-driver"

# stable-2.6.27.8 tag
SRCREV = "d74c8c68c38408e0cfc39ca9fbfe84ab8db3ad93"

PV = "2.6.27.8+${PR}+git${SRCREV}"
PR = "r2"

SRC_URI = "git://git.omapzoom.org/repo/omapkernel.git;branch=master;protocol=http"

SRC_URI_append_omap-zoom2-beta = " \
           file://zoom2-beta-disp-params.diff;patch=1 \
          "

S = "${WORKDIR}/git"

# You can supply your own defconfig if you like.  See
# http://bec-systems.com/oe/html/recipes_sources.html for a full explanation
#SRC_URI_omap-zoom2-beta += "file://defconfig-omap-zoom2-beta

do_stage_append() {
	install -d ${STAGING_KERNEL_DIR}/drivers/media/video/isp
	install -m 0644 ${S}/drivers/media/video/isp/*.h ${STAGING_KERNEL_DIR}/drivers/media/video/isp
}
