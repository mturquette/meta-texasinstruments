SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU/DSP Bridge libraries."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "virtual/dspbridge-module"

PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
FILES_${PN} = "${libdir}/libbridge.so ${libdir}/libbridge.so.2 ${libdir}/libqos.a ${libdir}/libqos.so.2"
FILES_${PN}-dev = "${includedir}/dspbridge"

SRCREV = "2d85c81f9c0d88b3f4eb519aade602dcd91d8d28"
SRC_URI = " \
	git://git.omapzoom.org/platform/hardware/ti/omap3.git;protocol=git \
	file://fmakefile \
	"
#	file://23.12-mkcross-api.patch;patch=1 
S = "${WORKDIR}/git/dspbridge"

do_unpack2() {
	cp -p ${FILESDIR}/fmakefile ${S}/api/Makefile
}

do_compile() {
	#mkdir ${S}/target
	cd ${S}/api
	oe_runmake PREFIX=${S} TGTROOT=${S} KRNLSRC=${STAGING_KERNEL_DIR} \
		BUILD=rel CMDDEFS='GT_TRACE DEBUG' V=1

	cd ${S}/api/bridge
	mv libbridge.so libbridge.so.2
	ln -s libbridge.so.2 libbridge.so

	# don't blame me -- i voted for kodos!
	cd ${S}/api/qos
	mv libqos.a libqos.so.2
	ln -s libqos.so.2 libqos.a
}

do_stage() {
	oe_libinstall -so -C ${S}/api/bridge libbridge ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/api/qos libqos ${STAGING_LIBDIR}
	install -d ${STAGING_INCDIR}/dspbridge
	install -m 0644 ${S}/api/inc/*.h ${STAGING_INCDIR}/dspbridge/
}

do_install() {
	oe_libinstall -so -C ${S}/api/bridge libbridge ${D}/${libdir}
	oe_libinstall -so -C ${S}/api/qos libqos ${D}/${libdir}
	install -d ${D}${includedir}/dspbridge
	install -m 0644 ${S}/api/inc/*.h ${D}${includedir}/dspbridge/
}

addtask unpack2 after do_unpack before do_patch
