SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "Imagination Technologies SGX Power VR OpenGL libs (no X support)"
LICENSE = "GPL"
PR = "r0"
COMPATIBLE_MACHINE = "omap-3430(l|s)dp|beagleboard"
RDEPENDS = sgx-kernel-module
DEPENDS = virtual/kernel

inherit ccasefetch

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "file://ddk_types_fix2.patch;patch=1 \
	file://noxsupport.patch;patch=1"

# We need to override this and make sure it's only -j1
#PARALLEL_MAKE = "-j1"

CCASE_SPEC = "%\
	element * COMPONENT_ROOT%\
	element /vobs/wtbu/OMAPSW_GFX/... GFX-LINUX-SGX-DDK-23X_RLS_1.3%\
	"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_GFX/IMAGINATION/GFX/GFX_Linux_DDK"
CCASE_PATHCOMPONENT = "GFX_Linux_DDK"
CCASE_PATHCOMPONENTS = "5"

# stupid hack.  Carlos knows to fix it in future revisions.
do_chmod() {
	chmod -R +w ${S}/src/eurasia
}

do_compile() {
    	echo -n "host compiler is "
	gcc --version
	echo -n "bison is at "
	which bison-1.875
	which flex

	cd ${S}/src/eurasia/eurasiacon/build/linux/omap3430_linux
	oe_runmake EURASIAROOT=${S}/src/eurasia KERNELDIR=${STAGING_KERNEL_DIR} \
		DISCIMAGE=${STAGING_DIR_TARGET} X11ROOT=${prefix} BISON=bison-1.875 V=1 SUPPORT_XWS=0
#		BISON=bison-1.875 V=1 SUPPORT_XWS=0
}

#do_stage () {
#    echo "staging is a lie."
#}

#do_install () {
#    echo "install is a lie."
#}

do_install() {
	install -d ${D}/lib/modules/2.6.24.7-omap1-arm2

	cd ${S}/src/eurasia/eurasiacon/build/linux/omap3430_linux
# FIXME: make install doesn't seem to work...
	oe_runmake EURASIAROOT=${S}/src/eurasia KERNELDIR=${STAGING_KERNEL_DIR} \
		DISCIMAGE=${D} X11ROOT=${prefix} CROSS=${AR%-*}- \
		BISON=bison-1.875 SUPPORT_XWS=0 install
}



do_stage() {
	install -d ${STAGING_LIBDIR}
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

addtask chmod after do_unpack_ccase before do_patch
