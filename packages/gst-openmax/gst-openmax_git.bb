DEPENDS = "gstreamer virtual/openmax-il"

SRC_URI = "git://github.com/felipec/gst-openmax.git;protocol=git \
	file://common-20090128.tar.gz \
	file://nogstcheck.patch;patch=1 \
	"
# From omap branch:
SRCREV = "022c451b3f8ac74b18edad29abc0a52d270e38b4"
S = "${WORKDIR}/git"

inherit autotools

EXTRA_OECONF += "--disable-valgrind"

do_configure_prepend() {
	mv ${WORKDIR}/common ${S}
}

FILES_${PN} += "${libdir}/gstreamer-0.10/libgstomx.so"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/libgstomx.*a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"
