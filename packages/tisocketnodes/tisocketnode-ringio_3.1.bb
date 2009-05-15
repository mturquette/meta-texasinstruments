PRIORITY = "optional"
DESCRIPTION = "Texas Instruments RingIO Socket Node."
LICENSE = "LGPL"
PR = "r1"
DEPENDS = "baseimage"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/system/ringio/... DSP-MM-TII-MM_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/system/ringio"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

ENV_VAR = "DEPOT=${STAGING_BINDIR_NATIVE}/dspbridge/tools \
	   DSPMAKEROOT=${S}/make \
	   DBS_BRIDGE_DIR_C64=${STAGING_BINDIR}/dspbridge/dsp \
	   DBS_SABIOS_DIR_C64=${STAGING_BINDIR_NATIVE}/dspbridge/tools \
	   DBS_CGTOOLS_DIR_C64=${STAGING_BINDIR_NATIVE}/dspbridge/tools/cgt6x-6.0.7 \
	   DBS_FC=${STAGING_BINDIR}/dspbridge/dsp/bdsptools/framework_components_1_10_04/packages-bld \
	   DLLCREATE_DIR=${STAGING_BINDIR}/DLLcreate \
"

#set to release or debug
RELEASE = "release"

inherit ccasefetch

do_compile() {
## Getting the Master Config files
	mkdir -p ${S}/include 
        cp -a ${STAGING_INCDIR}/dspbridge/include/* ${S}/include
## Getting the make system
	mkdir -p ${S}/make
        cp -a ${STAGING_BINDIR}/dspbridge/make/* ${S}/make
## Setting path to find gmake
        pathorig=$PATH
	export PATH=$PATH:${STAGING_BINDIR_NATIVE}/dspbridge/tools/xdctools
        chmod -R +w ${S}/*
	cd ${S}/system/ringio
	sed -e 's%\\%\/%g' makefile > makefile.linux
	${ENV_VAR} oe_runmake -f makefile.linux build=omap3430${RELEASE}
## Setting path to original value
	export PATH=$pathorig
        unset pathorig
}

do_stage() {
	install -d ${STAGING_BINDIR}/dspbridge/system/ringio
	cp -a ${S}/system/ringio/* ${STAGING_BINDIR}/dspbridge/system/ringio
}

do_install() {
	install -d ${D}${base_libdir}/dsp
	install -m 0644 ${S}/system/ringio/out/omap3430/${RELEASE}/ringio.dll64P ${D}${base_libdir}/dsp
}

FILES_${PN} = "${base_libdir}/dsp"
