SECTION = "x11/multimedia"
PRIORITY = "optional"
DEPENDS = "gtk+ gstreamer"
DESCRIPTION = "Webcam application modeled after Photobooth"
PR = "r0"

SRC_URI = "svn://svn.gnome.org/svn/cheese;module=trunk;proto=http;rev=1179"

S = "${WORKDIR}/trunk"

inherit pkgconfig autotools
