DESCRIPTION = "Bluetooth configuration applet"
LICENSE = "GPL+LGPL"

DEPENDS = "dbus-glib gconf libnotify gtk+ obex-data-server"
RRECOMMENDS = "gnome-icon-theme"

PR = "r4"

SRC_URI = "http://bluez.sourceforge.net/download/${P}.tar.gz"

#inherit autotools pkgconfig gconf mime
inherit autotools pkgconfig gconf

FILES_${PN} += "${datadir}/gconf ${datadir}/icons ${datadir}/mime/packages"


# we need to setup a couple extra files to have a matchbox-desktop
# launcher for bluetooth-sendto, and to have bluetooth-applet start
# automatically:
do_install_append() {
	# create file to start bluetooth-applet:
	mkdir -p ${D}/etc/X11/Xsession.d
	cat > ${D}/etc/X11/Xsession.d/90BluetoothApplet.sh <<_EOF
/usr/bin/bluetooth-applet --singleton &
_EOF

	# create launcher for bluetooth-sendto:
	mkdir -p ${D}/usr/share/applications
	cat > ${D}/usr/share/applications/bluetooth-sendto.desktop <<_EOF
[Desktop Entry]
Encoding=UTF-8
Name=Bluetooth Send
Comment=Send File over Bluetooth
Icon=bluetooth
Exec=bluetooth-sendto
Terminal=false
Type=Application
Categories=Utility;
OnlyShowIn=GNOME;
_EOF
}

