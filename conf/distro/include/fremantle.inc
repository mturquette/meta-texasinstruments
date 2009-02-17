# various Maemo4 specific libraries
PREFERRED_VERSION_mce-dev = "1.6.3"
PREFERRED_VERSION_libosso = "2.13"
PREFERRED_VERSION_libhildon = "1.99.0"
PREFERRED_VERSION_libhildonmime = "1.10.0"
PREFERRED_VERSION_libhildonfm = "1.9.46"
PREFERRED_VERSION_hildon-thumbnail = "0.14"
PREFERRED_VERSION_osso-gwconnect = "1.0.12"
PREFERRED_VERSION_osso-gnome-vfs2 = "2.16.3"

# Java stuff 
PREFERRED_PROVIDER_midpath = "midpath-maemo"
PREFERRED_PROVIDER_midpath-backend-gtk = "midpath-backend-gtk-hildon"

# Need jamvm-initial since cacao 0.98 seems to get miscompiled with newer GCCs.
PREFERRED_PROVIDER_virtual/java-initial = "jamvm-initial"

PREFERRED_PROVIDER_virtual/javac-native = "ecj-bootstrap-native"

PREFERRED_PROVIDER_classpath = "classpath"
PREFERRED_VERSION_classpath = "0.97.2"

PREFERRED_PROVIDER_swt3.4-gtk = "swt3.4-gtk-hildon"
PREFERRED_VERSION_swt3.4-gtk-hildon = "3.4"
