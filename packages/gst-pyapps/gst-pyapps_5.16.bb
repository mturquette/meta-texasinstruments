SECTION = "x11/multimedia"
PRIORITY = "optional"
RDEPENDS = "gst-goo python-gst python-pygtk python-threading python-pyalsaaudio"
DESCRIPTION = "GStreamer plug-ins for OpenMAX IL based on LibGoo"
PR = "r0"

CCASE_SPEC = "%\
element /vobs/wtbu/OMAPSW_L/mmframework/... MMFRAMEWORK_REL_${PV}%\
element * /main/LATEST%\
"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_L/mmframework/apps/pyapps"
CCASE_PATHCOMPONENT = "pyapps"
CCASE_PATHCOMPONENTS = 5

inherit ccasefetch

PACKAGES = "${PN} ${PN}-doc"
FILES_${PN} += " ${bindir}"
FILES_${PN}-doc += " ${docdir}"

do_install () {
	install -d ${D}${bindir}
	install -m 755 ${S}/audio-player.py ${D}${bindir}
	install -m 755 ${S}/muxer-app.py ${D}${bindir}
	install -m 755 ${S}/pipelines.py ${D}${bindir}
	install -m 755 ${S}/slideshow.py ${D}${bindir}
	install -m 755 ${S}/multishoot.py ${D}${bindir}

	install -d ${D}${docdir}
	install -m 644 ${S}/audio-player.txt ${D}${docdir}
	install -m 644 ${S}/muxer-app.txt ${D}${docdir}
	install -m 644 ${S}/slideshow.txt ${D}${docdir}
}
