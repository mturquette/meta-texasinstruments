SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU/DSP Bridge libraries."
LICENSE = "LGPL"
PR = "r1"
DEPENDS = "virtual/dspbridge-driver"

inherit ccasefetch

PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
FILES_${PN} = "${libdir}/libbridge.so ${libdir}/libbridge.so.2 ${libdir}/libqos.a ${libdir}/libqos.so.2"
FILES_${PN}-dev = "${includedir}/dspbridge"

#SRC_URI = "http://omapssp.dal.design.ti.com/VOBS/CSSD_Linux_Releases/3430/Linux_12.x/CSSD_Linux_${PV}RC1.tar.bz2 \
SRC_URI = " \
	file://mkcross-api.patch;patch=1 \
	"

#S = ${WORKDIR}/CSSD_Linux_${PV}/src/bridge/mpu

CCASE_SPEC = "%\
	element * COMPONENT_ROOT%\
	element /vobs/wtbu/OMAPSW_MPU/dspbridge/...  L-BRIDGE-MPU_RLS_${PV}%\
	element * /main/LATEST%\
	"
CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_MPU/dspbridge"
CCASE_PATHCOMPONENT = "dspbridge"
CCASE_PATHCOMPONENTS = "3"

#do_unpack() {
#	cd ${WORKDIR}
#	tar jxf ${DL_DIR}/CSSD_Linux_${PV}RC1.tar.bz2 CSSD_Linux_${PV}/src/bridge/mpu/mpu_api
#}

do_compile() {
	#mkdir ${S}/target
	cd ${S}/mpu_api/src
	oe_runmake PREFIX=${S} TGTROOT=${S} KRNLSRC=${STAGING_KERNEL_DIR} \
		BUILD=rel CMDDEFS='GT_TRACE DEBUG'

	cd ${S}/mpu_api/src/bridge
	mv libbridge.so libbridge.so.2
	ln -s libbridge.so.2 libbridge.so

	# don't blame me -- i voted for kodos!
	cd ${S}/mpu_api/src/qos
	mv libqos.a libqos.so.2
	ln -s libqos.so.2 libqos.a
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
	install -d ${D}${includedir}/dspbridge
	install -m 0644 ${S}/mpu_api/inc/*.h ${D}${includedir}/dspbridge/
}
