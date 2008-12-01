SECTION = "kernel/modules"
PRIORITY = "optional"
DESCRIPTION = "Imagination Technologies SGX Power VR module."
LICENSE = "GPL"
PR = "r5"
COMPATIBLE_MACHINE = "omap-3430(l|s)dp|beagleboard"

inherit module

SRC_URI = "file:///home/mturquette/src/GFX_Linux_DDK.tar.gz"
S="${WORKDIR}/GFX_Linux_DDK"

#CCASE_SPEC = "%\
#	element * COMPONENT_ROOT%\
#	element /vobs/wtbu/OMAPSW_GFX/... GFX-LINUX-SGX-DDK-23X_RLS_1.2%\
#	"
#CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_GFX/IMAGINATION/GFX/GFX_Linux_DDK"
#CCASE_PATHCOMPONENT = "GFX_Linux_DDK"
#CCASE_PATHCOMPONENTS = "5"

do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	cd ${S}/src/eurasia_km/eurasiacon/build/linux/omap3430_linux/kbuild
# FIXME: X11ROOT be set to IMG's supplied libs in the future when we start
#		using the accelerated KDrive.  For now we're using stock X.org 1.4
	oe_runmake EURASIAROOT=${S}/src/eurasia_km KERNELDIR=${STAGING_KERNEL_DIR} \
		DISCIMAGE=${STAGING_DIR_TARGET} X11ROOT=${prefix} CROSS=${AR%-*}-
}

do_install() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	cd ${S}/src/eurasia_km/eurasiacon/build/linux/omap3430_linux/kbuild
	oe_runmake EURASIAROOT=${S}/src/eurasia_km KERNELDIR=${STAGING_KERNEL_DIR} \
		DISCIMAGE=${D} X11ROOT=${prefix} CROSS=${AR%-*}- \
		install
}
