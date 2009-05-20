DESCRIPTION = "Gnome Bluetooth"
LICENSE = "GNU+LGPL"

DEPENDS = "libbtctl libgnomeui gconf python-pygtk gob2-native"

PR = "r0"

inherit gnome
inherit autotools pkgconfig gconf

SRC_URI = "http://download.gnome.org/sources/gnome-bluetooth/0.11/gnome-bluetooth-${PV}.tar.bz2 \
	   file://python-pygtk-version.patch;patch=1 \
	  "
	

FILES_${PN} +="${datadir}/gconf ${libdir}/libgnomebt.so* ${bindir}/* ${sharedir}/gnome-bluetooth/pixmaps/*"

do_compile(){	
	oe_runmake \
	CODEGENDIR="${STAGING_DATADIR}/pygtk/2.0/codegen" \
	DEFSDIR="${STAGING_DATADIR}/pygtk/2.0/defs"
}

do_qa_staging(){
	
	#Look for missing gconf directory
	install -d ${STAGING_ETCDIR_NATIVE}/gconf/
}


