DESCRIPTION = "Bluetooth helper library for Glib/GTK/GNOME applications"
LICENSE="GNU+GPL"

DEPENDS = "glib-2.0 bluez-libs openobex"

PR = "r0"

SRC_URI = "http://ftp.gnome.org/pub/GNOME/sources/libbtctl/0.10/libbtctl-${PV}.tar.bz2"

inherit gnome
inherit pkgconfig autotools gconf

S = "${WORKDIR}/libbtctl-${PV}"

CFLAGS_prepend = "-L${STAGING_LIBDIR}"

do_compile_prepend(){
	# To resolve breakage in build
	cd ${S}/src
	make
}

do_install_prepend(){

	# Header files required to build the gnome-bluetooth package

       install -m 0755 ${S}/src/btctl.h ${STAGING_INCDIR}
       install -m 0755 ${S}/src/btctl-discovery-source.h ${STAGING_INCDIR}
       install -m 0755 ${S}/src/btobex.h ${STAGING_INCDIR}
       install -m 0755 ${S}/src/btctl-types.h ${STAGING_INCDIR}
       install -m 0755 ${S}/src/obex-server-source.h ${STAGING_INCDIR}

        # To include missing libraries

       install -m 0755 ${S}/src/.libs/libbtctl.so ${STAGING_LIBDIR}
       install -m 0755 ${S}/src/.libs/libbtctl.so.4 ${STAGING_LIBDIR}
       install -m 0755 ${S}/src/.libs/libbtctl.so.4.1.0 ${STAGING_LIBDIR}
}

