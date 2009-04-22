SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "Imagination Technologies SGX Power VR OpenGL libs (no X support)"
LICENSE = "GPL"
PR = "r1"
COMPATIBLE_MACHINE = "omap-3430(l|s)dp|beagleboard"
RDEPENDS = sgx-kernel-module
DEPENDS = virtual/kernel

inherit ccasefetch

PACKAGE_ARCH = "${MACHINE_ARCH}"
#PACKAGES = "${PN} ${PN}-dbg"
#FILES_${PN} = "${bindir} ${libdir} ${sysconfdir} ${libdir}/*.so*"

SRC_URI = "file://noxsupport.patch;patch=1"

CCASE_SPEC = "%\
	element * COMPONENT_ROOT%\
	element /vobs/wtbu/OMAPSW_GFX/... GFX-LINUX-SGX-DDK-23X_RLS_${PV}%\
	element * /main/LATEST%\
	"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_GFX/IMAGINATION/GFX/GFX_Linux_DDK"
CCASE_PATHCOMPONENT = "GFX_Linux_DDK"
CCASE_PATHCOMPONENTS = "5"

# stupid hack.  Carlos knows to fix it in future revisions.
do_chmod() {
	chmod -R +w ${S}/src/eurasia
}

do_compile() {
	cd ${S}/src/eurasia/eurasiacon/build/linux/omap3430_linux
	oe_runmake EURASIAROOT=${S}/src/eurasia KERNELDIR=${STAGING_KERNEL_DIR} \
		DISCIMAGE=${STAGING_DIR_TARGET} X11ROOT=${prefix} BISON=bison-1.875 V=1 SUPPORT_XWS=0
}

do_install() {
	cd ${S}/src/eurasia/eurasiacon/binary_omap3430_linux_release
	
	install -d ${D}/lib/modules/2.6.24.7-omap1-arm2
	install -d ${D}${libdir}
	
	oe_libinstall -so libGLES_CM ${D}${libdir}
	oe_libinstall -so libGLESv2 ${D}${libdir}
	oe_libinstall -so libglslcompiler ${D}${libdir}
	oe_libinstall -so libOpenVG ${D}${libdir}
	oe_libinstall -so libOpenVGU ${D}${libdir}
	oe_libinstall -so libIMGegl ${D}${libdir}
	oe_libinstall -so libEGL ${D}${libdir}
	oe_libinstall -so libpvr2d ${D}${libdir}
	oe_libinstall -so libsrv_um ${D}${libdir}
	oe_libinstall -so libpvrscope ${D}${libdir}
	oe_libinstall -so libpvrPVR2D_X11WSEGL ${D}${libdir}
	oe_libinstall -so libpvrPVR2D_BLITWSEGL ${D}${libdir}
	oe_libinstall -so libpvrPVR2D_FLIPWSEGL ${D}${libdir}
	oe_libinstall -so libpvrPVR2D_FRONTWSEGL ${D}${libdir}

	install -d ${D}${bindir}
	install -m 755 pvrsrvinit ${D}${bindir}
	install -m 755 sgx_init_test ${D}${bindir}
	install -m 755 gles2test1 ${D}${bindir}
	install -m 755 gles1test1 ${D}${bindir}
	install -m 755 gles1_texture_stream ${D}${bindir}
	install -m 755 gles2_texture_stream ${D}${bindir}
	install -m 755 ovg_unit_test ${D}${bindir}
	install -m 755 services_test ${D}${bindir}
	install -m 755 sgx_blit_test ${D}${bindir}
	install -m 755 sgx_flip_test ${D}${bindir}
	install -m 755 sgx_render_flip_test ${D}${bindir}
	install -m 755 sgx_render_test ${D}${bindir}
	install -m 755 pvr2d_test ${D}${bindir}
	install -m 755 eglinfo ${D}${bindir}

	install -m 644 glsltest1_vertshader.txt ${D}${bindir}
	install -m 644 glsltest1_fragshaderA.txt ${D}${bindir}
	install -m 644 glsltest1_fragshaderB.txt ${D}${bindir}

	install -d ${D}${sysconfdir}/init.d
	install -m 755 rc.pvr ${D}${sysconfdir}/init.d/

	sed -i "s#local/##" ${D}${sysconfdir}/init.d/rc.pvr

#	cd ${S}/src/eurasia/eurasiacon/build/linux/omap3430_linux
# FIXME: make install doesn't seem to work...
#	oe_runmake EURASIAROOT=${S}/src/eurasia KERNELDIR=${STAGING_KERNEL_DIR} \
#		DISCIMAGE=${D} X11ROOT=${prefix} CROSS=${AR%-*}- \
#		BISON=bison-1.875 SUPPORT_XWS=0 install
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

addtask chmod after do_unpack_ccase before do_patch
