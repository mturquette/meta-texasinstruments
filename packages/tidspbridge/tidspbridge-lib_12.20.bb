SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU/DSP Bridge libraries."
LICENSE = "LGPL"
PR = "r1"
DEPENDS = "tidspbridge-module"

PACKAGES = "${PN} ${PN}-dbg"
FILES_${PN} = "${libdir}/libbridge.so ${libdir}/libbridge.so.2 ${libdir}/libqos.so ${libdir}/libqos.so.2"

SRC_URI = "http://omapssp.dal.design.ti.com/VOBS/CSSD_Linux_Releases/3430/Linux_12.x/CSSD_Linux_${PV}RC1.tar.bz2 \
	file://mkcross-api.patch;patch=1 \
	file://mkshlib-api.patch;patch=1"

S = ${WORKDIR}/CSSD_Linux_${PV}/src/bridge/mpu

do_unpack() {
	cd ${WORKDIR}
	tar jxf ${DL_DIR}/CSSD_Linux_${PV}RC1.tar.bz2 CSSD_Linux_${PV}/src/bridge/mpu/mpu_api
}

do_compile() {
	mkdir ${S}/target
	cd ${S}/mpu_api/src
	oe_runmake PREFIX=${S} TGTROOT=${S} KRNLSRC=${STAGING_KERNEL_DIR} \
		BUILD=rel CMDDEFS='GT_TRACE DEBUG'

	cd ${S}/mpu_api/src/bridge
	mv libbridge.so libbridge.so.2
	ln -s libbridge.so.2 libbridge.so

	cd ${S}/mpu_api/src/qos
	mv libqos.so libqos.so.2
	ln -s libqos.so.2 libqos.so
}

do_stage() {
	oe_libinstall -so -C ${S}/mpu_api/src/bridge libbridge ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/mpu_api/src/qos libqos ${STAGING_LIBDIR}
	install -d ${STAGING_INCDIR}/dspbridge
	install -m 0644 ${S}/mpu_api/inc/*.h ${STAGING_INCDIR}/dspbridge/
}

do_install() {
	oe_libinstall -so -C ${S}/mpu_api/src/bridge libbridge ${D}/${libdir}
	oe_libinstall -so -C ${S}/mpu_api/src/qos libqos ${D}/${libdir}
}
