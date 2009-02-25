SECTION = "x11/multimedia"
PRIORITY = "optional"
DEPENDS = "gtk+ gstreamer gnome-doc-utils intltool-native libgnomeui"
#DEPENDS = "gtk+ gstreamer gnome-doc-utils intltool-native libgnomeui"
DESCRIPTION = "Webcam application modeled after Photobooth"
PR = "r0"

EXTRA_OECONF += "--disable-scrollkeeper --disable-gtk-doc --disable-docbook-docs --disable-docbook --disable-xml-docs --disable-doxygen-docs --disable-docs --disable-doc --disable-build-docs --disable-help --disable-omf --with-omf-dir=${STAGING_DATADIR}/omf --with-help-dir=${STAGING_DATADIR}/gnome/help "
do_configure_prepend () {
	echo "which intltool-extract is `which intltool-extract`"
}

inherit gnome
