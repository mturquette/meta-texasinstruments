DEPENDS = "gstreamer"
COMPATIBLE_MACHINE = "omap-3430(l|s)dp|beagleboard"

inherit module pkgconfig

SRC_URI = "git://github.com/felipec/gst-omapfb.git;protocol=http \
	"
SRCREV = "6f0b1cb50d1c67c3a3db2f11246256060ac871de"
S = "${WORKDIR}/git"

do_compile() {
	THE_GST_LIBS=${STAGING_LIBDIR}
	THE_GST_CFLAGS=${STAGING_INCDIR}/gstreamer-0.10
	oe_runmake V=1 \
		KERNEL=${STAGING_KERNEL_DIR} \
		CFLAGS="-Wall -ggdb -ansi -std=c99 \
			-I${STAGING_INCDIR}/gstreamer-0.10 \
			-I${STAGING_INCDIR}/glib-2.0 \
			-I${STAGING_INCDIR}/libxml2 \
			-I${STAGING_KERNEL_DIR} \
			"
}

#FILES_${PN} += "${libdir}/gstreamer-0.10/libgstomx.so"
#FILES_${PN}-dev += "${libdir}/gstreamer-0.10/libgstomx.*a"
#FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"
