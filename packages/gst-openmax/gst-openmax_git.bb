DEPENDS = "gstreamer virtual/openmax-il"

SRC_URI = "git://github.com/felipec/gst-openmax.git;protocol=git \
	file://common-20090128.tar.gz \
	file://nogstcheck.patch;patch=1 \
	file://colorformatdec.patch;patch=1"
# From omap branch:
SRCREV = "18fdb2371e1e893daa40af31da21026bb32a72f5"
S = "${WORKDIR}/git"

inherit autotools

EXTRA_OECONF += "--disable-valgrind"

do_configure_prepend() {
	mv ${WORKDIR}/common ${S}
}

FILES_${PN} += "${libdir}/gstreamer-0.10/libgstomx.so"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/libgstomx.*a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"
