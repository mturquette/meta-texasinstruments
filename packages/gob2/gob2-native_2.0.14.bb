LICENSE = "LGPL"
DESCRIPTION = "A library to make creating GObjects easier"
HOMEPAGE = "http://www.5z.com/jirka/gob.html"
SECTION  = "libs"

SRC_URI = "http://ftp.5z.com/pub/gob/old/gob2-${PV}.tar.gz"

S = "${WORKDIR}/gob2-${PV}"

inherit autotools
inherit native

PR = "r2"
 
DEPENDS = "bison-native flex-native"


