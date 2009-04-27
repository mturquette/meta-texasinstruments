DESCRIPTION = "GStreamer plug-in for omapfb rendering."
AUTHOR = "Felipe Contreras <felipe.contreras@nokia.com>"
LICENSE = "LGPL"
DEPENDS = "gstreamer"
COMPATIBLE_MACHINE = "omap-3430(l|s)dp|beagleboard"
PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN} ${PN}-dbg"

inherit pkgconfig

SRC_URI = "git://github.com/felipec/gst-omapfb.git;protocol=http \
	file://headerinc.patch;patch=1 \
	"
SRCREV = "6f0b1cb50d1c67c3a3db2f11246256060ac871de"
S = "${WORKDIR}/git"

do_compile() {
	oe_runmake V=1 \
		CFLAGS="-Wall -ggdb -ansi -std=c99 \
			-I${STAGING_INCDIR}/gstreamer-0.10 \
			-I${STAGING_INCDIR}/glib-2.0 \
			-I${STAGING_INCDIR}/libxml2 \
			-I${STAGING_KERNEL_DIR}/include \
			"
}

do_install() {
	install -d ${D}/usr/lib/gstreamer-0.10
	oe_runmake V=1 DESTDIR=${D} install
}

FILES_${PN} += "${libdir}/gstreamer-0.10/libgstomapfb.so"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"
