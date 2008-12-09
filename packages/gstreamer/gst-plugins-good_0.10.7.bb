require gst-plugins.inc
DEPENDS += "gst-plugins-base gconf cairo jpeg libpng gtk+ zlib libid3tag flac \
	    speex"
PR = "r4"

SRC_URI += "file://matroska-amr-0.10.7.patch;patch=1"

EXTRA_OECONF += "--disable-aalib --disable-esd --disable-shout2 --disable-libcaca --without-check \
	--enable-gst_v4l2 --enable-xvideo --enable-experimental"

PACKAGES += "gst-plugin-id3demux"

do_configure_prepend() {
	# This m4 file contains nastiness which conflicts with libtool 2.2.2
	rm ${S}/m4/lib-link.m4 || true
}
