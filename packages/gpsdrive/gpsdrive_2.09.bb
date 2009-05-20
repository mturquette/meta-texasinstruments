DESCRIPTION = "GPS Drive MAP application Bitbake"
LICENSE = "GPL"
SECTION = "base"
PR = "r1"
 
DEPENDS += "libpcre-native pcre gtk+"
 
SRC_URI = "http://www.gpsdrive.de/packages/gpsdrive-${PV}.tar.gz \
	       file://initscript/gpsdrive.ini "
inherit autotools pkgconfig
 
EXTRA_OECONF = " \
        --disable-garmin \
        --target=arm-none-linux-gnueabi \
        --host=i686-pc-linux-gnu "
 
LDFLAGS += "-L${STAGING_LIBDIR}"
DFLAGS = "-L/usr/lib/"
 
do_install_append(){

 install -d ${D}${bindir}/gps
 install -m 0755 ${S}/../initscript/gpsdrive.ini  ${D}${datadir}/applications/

#remove gpsd from bin dir, we have it installed separately in sbin dir
 rm ${D}${bindir}/gpsd

# remove this default file, we will create our own
 rm ${D}${datadir}/applications/gpsdrive.desktop

 cat > ${D}/usr/share/applications/gpsdrive.desktop <<_EOF
[Desktop Entry]
Encoding=UTF-8
Name=GpsDrive
Comment=GPS Navigation
Comment[de]=GPS Navigationprogram
Exec="/usr/share/applications/gpsdrive.ini"
Icon=gpsicon.png
Terminal=false
Type=Application
Categories=Application;Network;
StartupNotify=true
_EOF

}
