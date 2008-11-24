SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "Imagination Technologies SGX Power VR module."
LICENSE = "GPL"
PR = "r2"
COMPATIBLE_MACHINE = "omap-3430(l|s)dp|beagleboard"
RDEPENDS = sgx-pvr-module
DEPENDS = bison-native-1.875 virtual/kernel

SRC_URI =	"file:///home/mturquette/src/GFX_Linux_DDK.tar.gz \
			file://ddk_types_fix2.patch;patch=1 \
			"

#SRC_URI = "git:///home/mturquette/src/GFX_Linux_DDK/"
S="${WORKDIR}/GFX_Linux_DDK"

#CCASE_SPEC = "%\
#	element * COMPONENT_ROOT%\
#	element /vobs/wtbu/OMAPSW_GFX/... GFX-LINUX-SGX-DDK-23X_RLS_1.2%\
#	"
#CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_GFX/IMAGINATION/GFX/GFX_Linux_DDK"
#CCASE_PATHCOMPONENT = "GFX_Linux_DDK"
#CCASE_PATHCOMPONENTS = "5"

# stupid hack.  Carlos knows to fix it in future revisions.
do_chmod() {
	chmod -R +w ${S}/src/eurasia/
}

do_compile() {
	cd ${S}/src/eurasia/eurasiacon/build/linux/omap3430_linux
# FIXME: X11ROOT be set to IMG's supplied libs in the future when we start
#		using the accelerated KDrive.  For now we're using stock X.org 1.4
#	oe_runmake EURASIAROOT=${S}/src/eurasia KERNELDIR=${STAGING_KERNEL_DIR} \
	oe_runmake EURASIAROOT=${S}/src/eurasia KERNELDIR=${STAGING_DIR}/omap-3430ldp-none-linux-gnueabi/kernel \
		DISCIMAGE=${STAGING_DIR_TARGET} X11ROOT=${prefix} BISON=${STAGING_DIR_NATIVE}/usr/local/bin/bison-native-1.875 V=1
		#DISCIMAGE=${STAGING_DIR_TARGET} X11ROOT=${prefix} CROSS=${AR%-*}- \
		#PATH=${STAGE_DIR_NATIVE}/usr/local/bin:$PATH BISON=${STAGING_DIR_NATIVE}/usr/local/bin/bison-native-1.875 V=1
}

#do_install() {
#	echo "doing nothing"
#}

do_install() {
	cd ${S}/src/eurasia/eurasiacon/build/linux/omap3430_linux
# FIXME: make install doesn't seem to work...
	oe_runmake EURASIAROOT=${S}/src/eurasia KERNELDIR=${STAGING_KERNEL_DIR} \
		DISCIMAGE=${D} X11ROOT=${prefix} CROSS=${AR%-*}- \
		PATH=${STAGE_DIR_TARGET}/usr/local/bin:$PATH BISON=bison-1.875 install
}

do_stage() {
#	    oe_libinstall -so -C ${S}/mpu_api/src/bridge libbridge ${STAGING_LIBDIR}
#		oe_libinstall -so -C ${S}/mpu_api/src/qos libqos ${STAGING_LIBDIR}
#		install -d ${STAGING_INCDIR}/dspbridge
#		install -m 0644 ${S}/mpu_api/inc/*.h ${STAGING_INCDIR}/dspbridge/

	#oe_libinstall -so -C src/eurasia/eurasiacon/binary_omap3430_linux_release
	cd ${S}/src/eurasia/eurasiacon/binary_omap3430_linux_release
	oe_libinstall -so libGLES_CM ${STAGING_LIBDIR}
	oe_libinstall -so libGLESv2 ${STAGING_LIBDIR}
	oe_libinstall -so libglslcompiler ${STAGING_LIBDIR}
	oe_libinstall -so libOpenVG ${STAGING_LIBDIR}
	oe_libinstall -so libOpenVGU ${STAGING_LIBDIR}
	oe_libinstall -so libIMGegl ${STAGING_LIBDIR}
	oe_libinstall -so libEGL ${STAGING_LIBDIR}
	oe_libinstall -so libpvr2d ${STAGING_LIBDIR}
	oe_libinstall -so libsrv_um ${STAGING_LIBDIR}
	oe_libinstall -so libpvrscope ${STAGING_LIBDIR}
	oe_libinstall -so libpvrPVR2D_X11WSEGL ${STAGING_LIBDIR}
	oe_libinstall -so libpvrPVR2D_BLITWSEGL ${STAGING_LIBDIR}
	oe_libinstall -so libpvrPVR2D_FLIPWSEGL ${STAGING_LIBDIR}
	oe_libinstall -so libpvrPVR2D_FRONTWSEGL ${STAGING_LIBDIR}

}

#do_install() {
#	    oe_libinstall -so -C ${S}/mpu_api/src/bridge libbridge ${D}/${libdir}
#		    oe_libinstall -so -C ${S}/mpu_api/src/qos libqos ${D}/${libdir}
#			    install -d ${D}${includedir}/dspbridge
#				    install -m 0644 ${S}/mpu_api/inc/*.h ${D}${includedir}/dspbridge/
#}

addtask chmod after do_unpack before do_patch
