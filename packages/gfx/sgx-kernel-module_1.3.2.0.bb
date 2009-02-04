SECTION = "kernel/modules"
PRIORITY = "optional"
DESCRIPTION = "Imagination Technologies SGX Power VR module."
LICENSE = "GPL"
PR = "r0"
COMPATIBLE_MACHINE = "omap-3430(l|s)dp|beagleboard"

inherit module ccasefetch

CCASE_SPEC = "%\
	element * COMPONENT_ROOT%\
	element /vobs/wtbu/OMAPSW_GFX/... GFX-LINUX-SGX-DDK-23X_RLS_${PV}%\
	element * /main/LATEST%\
	"
CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_GFX/IMAGINATION/GFX/GFX_Linux_DDK/src/eurasia_km"
CCASE_PATHCOMPONENT = "eurasia_km"
CCASE_PATHCOMPONENTS = "7"

do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	cd ${S}/eurasiacon/build/linux/omap3430_linux/kbuild
	oe_runmake EURASIAROOT=${S} KERNELDIR=${STAGING_KERNEL_DIR} \
		DISCIMAGE=${STAGING_DIR_TARGET} X11ROOT=${prefix} CROSS=${AR%-*}-
}

do_install() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	cd ${S}/eurasiacon/build/linux/omap3430_linux/kbuild
	oe_runmake EURASIAROOT=${S} KERNELDIR=${STAGING_KERNEL_DIR} \
		DISCIMAGE=${D} X11ROOT=${prefix} CROSS=${AR%-*}- \
		install
}
