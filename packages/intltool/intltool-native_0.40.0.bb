require intltool_${PV}.bb

PR = "r2"

inherit native
DEPENDS = "libxml-parser-perl-native"

export PERL = "/usr/bin/env perl"
#SRC_URI_append = " file://intltool-nowarn.patch;patch=1"
SRC_URI_append = " \
		file://intltool-extract-perl.patch;patch=1 \
		file://intltool-merge-perl.patch;patch=1 \
		file://intltool-prepare-perl.patch;patch=1 \
		file://intltool-update-perl.patch;patch=1 \
		"

RRECOMMENDS = ""
