DEPENDS = "tidspbridge-lib mm-isp"
DESCRIPTION = "Texas Instruments OpenMAX IL."
PR = "r1"
PACKAGES = "${PN}-dbg ${PN}-dev ${PN}-patterns ${PN}"

CCASE_SPEC = "\
	# OMX Audio%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/... LINUX-MMAUDIO_RLS_3.17%\
	# OMX Video%\
	element /vobs/wtbu/OMAPSW_MPU/linux/video/... LINUX-MMVIDEO_RLS_3.17%\
	# OMX Image%\
	element /vobs/wtbu/OMAPSW_MPU/linux/image/... LINUX-MMIMAGE_RLS_3.17%\
	# LCML & core%\
	element /vobs/wtbu/OMAPSW_MPU/linux/system/... LINUX-MMSYSTEM_RLS_3.17%\
	# OMX Application%\
	element /vobs/wtbu/OMAPSW_MPU/linux/application/... LINUX-MMAPPLICATION_RLS_3.17%\
	# ROOT folder & Make files%\
	element /vobs/wtbu/OMAPSW_MPU/linux/... LINUX-MMROOT_RLS_3.17%\
	\
	element * /main/LATEST%\
	"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_MPU/linux /vobs/wtbu/OMAPSW_MPU/algo"
CCASE_PATHCOMPONENTS = 3
CCASE_PATHCOMPONENT = "linux"


inherit pkgconfig ccasefetch

SRC_URI = "\
	file://libomxil-ti.pc \
	file://omap24xxvout.h \
	file://ampthread.patch;patch=1 \
	file://pmpthread.patch;patch=1 \
	file://23.10-rmmake.patch;patch=1 \
	file://pcmencmk.patch;patch=1 \
	file://wmadecmk.patch;patch=1 \
	file://g722encmk.patch;patch=1 \
	file://23.10-g729encperf.patch;patch=1 \
	file://jpegdecmk.patch;patch=1 \
	file://jpegencmk.patch;patch=1 \
	file://23.10-postprocperf.patch;patch=1 \
	file://23.11-vppmake.patch;patch=1 \
	file://videoencmk.patch;patch=1 \
	file://videodecmk.patch;patch=1 \
	file://23.11-cameramk.patch;patch=1 \
	"
# these pending update for 23.10/23.11:
#	file://wbamrencnorm.patch;patch=1 \
#	file://postprocnorm.patch;patch=1 \
#	file://videoencnorm.patch;patch=1 \
#	file://vppnorm.patch;patch=1 \
#	file://sysmake.patch;patch=1 \
# these pending update for 23.11:
#	file://aacdecnorm.patch;patch=1 \
#	file://23.10-aacencnorm.patch;patch=1 \
#	file://23.10-nbamrdecnorm.patch;patch=1 \
#	file://23.10-nbamrencnorm.patch;patch=1 \
#	file://23.10-wbamrdecnorm.patch;patch=1 \
#	file://23.10-addcommon.patch;patch=1 \
##	file://nocamera.patch;patch=1 \
##	file://nocameraapps.patch;patch=1 \


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
	oe_libinstall -so -C ${S}/system/src/openmax_il/omx_core/src libOMX_Core ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/system/src/openmax_il/lcml/src libLCML ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/system/src/openmax_il/clock_source/src libOMX_Clock ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/system/src/openmax_il/resource_manager_proxy/src libOMX_ResourceManagerProxy ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/system/src/openmax_il/resource_manager/resource_activity_monitor/src libRAM ${STAGING_LIBDIR}
	install -d ${STAGING_INCDIR}/omx
	install -m 0644 ${S}/system/src/openmax_il/omx_core/inc/*.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/system/src/openmax_il/audio_manager/inc/AudioManagerAPI.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/system/src/openmax_il/clock_source/inc/OMX_Clock.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/video/src/openmax_il/post_processor/inc/OMX_PostProc_CustomCmd.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/audio/src/openmax_il/aac_dec/inc/TIDspOmx.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/video/src/openmax_il/camera/inc/OMX_Camera.h ${STAGING_INCDIR}/omx/
	install -m 0644 ${S}/system/src/openmax_il/common/inc/OMX_TI_Common.h ${STAGING_INCDIR}/omx/
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

FILES_${PN} = "\
	/lib \
	/bin \
	/omx \
	"

FILES_${PN}-patterns = "\
	/omx/patterns \
	"

FILES_${PN}-dbg = "\
	/omx/.debug \
	/bin/.debug \
	/lib/.debug \
	"

FILES_${PN}-dev = "\
	/include \
	"
