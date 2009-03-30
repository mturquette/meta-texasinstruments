DEPENDS = "gstreamer virtual/openmax-il"
PR = "r1"

SRC_URI = "git://github.com/felipec/gst-openmax.git;protocol=git"
# From omap branch:
SRCREV = "0d7e0e16fb921041c3688f5c70edff6e2353e516"
S = "${WORKDIR}/git"

inherit autotools

EXTRA_OECONF += "--disable-valgrind"

FILES_${PN} += "${libdir}/gstreamer-0.10/libgstomx.so"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/libgstomx.*a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"
