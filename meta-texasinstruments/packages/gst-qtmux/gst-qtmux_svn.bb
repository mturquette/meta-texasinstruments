SECTION = "multimedia"
PRIORITY = "optional"
DEPENDS = "glib-2.0 gstreamer"
DESCRIPTION = "QT Muxer plug-in for GStreamer"
LICENSE = "LGPL"
PV = "0.10.0+svnr${SRCREV}"
PR = "r1"
S = ${WORKDIR}/${PN}

SRC_URI = "svn://anonsvn:anonsvn@gforge.embedded.ufcg.edu.br/svn/qtmux/trunk;module=${PN};rev=58;proto=http"

inherit autotools pkgconfig

FILES_${PN} += "${libdir}/gstreamer-0.10/libgstgoo.so"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/libgstgoo.*a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"

do_stage() {
	autotools_stage_all
}
