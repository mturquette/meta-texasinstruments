PRIORITY = "optional"
DEPENDS = "tidspbridge-lib"
DESCRIPTION = "Texas Instruments OpenMAX IL."
LICENSE = "LGPL"
PR = "r0"
PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"

S = ${WORKDIR}/src/omx/linux

inherit pkgconfig

#http://omapssp.dal.design.ti.com/VOBS/CSSD_Linux_Releases/3430/Linux_23.x/CSSD_Linux_${PV}RC4.tar.bz2 \

SRC_URI = "\
	file://home/ddiaz/downs/CSSD_Linux_${PV}-omx-nopatterns.tar.gz \
	file://libomxil-ti.pc \
	file://omap24xxvout.h \
	file://aacdecnorm.patch;patch=1 \
	file://23.10-aacencnorm.patch;patch=1 \
	file://23.10-nbamrdecnorm.patch;patch=1 \
	file://23.10-nbamrencnorm.patch;patch=1 \
	file://23.10-wbamrdecnorm.patch;patch=1 \
	file://ampthread.patch;patch=1 \
	file://pmpthread.patch;patch=1 \
	file://pcmencmk.patch;patch=1 \
	file://wmadecmk.patch;patch=1 \
	file://g722encmk.patch;patch=1 \
	file://23.10-g729encperf.patch;patch=1 \
	file://jpegdecmk.patch;patch=1 \
	file://jpegencmk.patch;patch=1 \
	file://nocamera.patch;patch=1 \
	file://videoencmk.patch;patch=1 \
	file://videodecmk.patch;patch=1 \
	file://nocameraapps.patch;patch=1 \
	file://23.10-rmmake.patch;patch=1 \
	file://23.10-addcommon.patch;patch=1 \
	file://23.10-postprocperf.patch;patch=1 \
	"
# these pending update for 23.10:
#	file://wbamrencnorm.patch;patch=1 \
#	file://postprocnorm.patch;patch=1 \
#	file://videoencnorm.patch;patch=1 \
#	file://vppnorm.patch;patch=1 \
#	file://sysmake.patch;patch=1 \


do_unpack() {
	cd ${WORKDIR}
#	tar jxf ${DL_DIR}/CSSD_Linux_${PV}RC4.tar.bz2 CSSD_Linux_${PV}RC4/src/omx/linux --strip-components 1
	tar xf /home/ddiaz/downs/CSSD_Linux_${PV}-omx-nopatterns.tar.gz CSSD_Linux_${PV}RC4/src/omx --strip-components 1
}

do_compile_prepend() {
	install -m 0644 ${FILESDIR}/omap24xxvout.h ${S}/video/src/openmax_il/post_processor/inc/omap24xxvout.h
	install -m 0644 ${FILESDIR}/libomxil-ti.pc ${S}/libomxil.pc
	install -d ${D}/omx
}

do_compile() {
	oe_runmake \
		PREFIX=${D} PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${D} OMXROOT=${S}
}

do_stage() {
	# FIXME: more headers are needed
	# TODO: can we make install to stagingdir?
	install -d ${STAGING_INCDIR}/omx
	install -m 0644 ${S}/system/src/openmax_il/omx_core/inc/*.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/system/src/openmax_il/audio_manager/inc/AudioManagerAPI.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/system/src/openmax_il/clock_source/inc/OMX_Clock.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/video/src/openmax_il/post_processor/inc/OMX_PostProc_CustomCmd.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/audio/src/openmax_il/aac_dec/inc/TIDspOmx.h ${STAGING_INCDIR}/omx/
}

do_install() {
	install -d ${D}/omx
	install -d ${D}/lib
	install -d ${D}/bin
	oe_runmake \
		PREFIX=${D} PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${D} OMXROOT=${S} \
		install
}

FILES_${PN}-dbg = "\
	/omx/.debug \
	/bin/.debug \
	/lib/.debug \
	"

FILES_${PN} = "\
	/lib \
	/bin \
	/omx \
	"

FILES_${PN}-dev = "\
	/include \
	"
