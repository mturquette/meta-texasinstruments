require gst-plugins.inc
DEPENDS += "gst-plugins-base libid3tag libmad mpeg2dec liba52 lame amrnb"
PR = "r0"

SRC_URI += "file://amrnbparse-seek.patch;patch=1 file://asfdemux-0.10.11.patch;patch=1"

EXTRA_OECONF += "--with-plugins=a52dec,lame,id3tag,mad,mpeg2dec,mpegstream,mpegaudioparse,asfdemux,realmedia,amrnb"

do_configure_prepend() {
	# This m4 file contains nastiness which conflicts with libtool 2.2.2
	rm ${S}/m4/lib-link.m4 || true
}
