PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Conversion library."
LICENSE = "LGPL"
PR = "r1"
DEPENDS = "baseimage tisocketnode-usn"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/video/... DSP-MM-TII-IMVID_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/video/lib/conversions"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

ENV_VAR = "DEPOT=${STAGING_BINDIR_NATIVE}/dspbridge/tools \
	   DSPMAKEROOT=${S}/make \
	   DBS_BRIDGE_DIR_C64=${STAGING_BINDIR}/dspbridge/dsp \
	   DBS_SABIOS_DIR_C64=${STAGING_BINDIR_NATIVE}/dspbridge/tools \
	   DBS_CGTOOLS_DIR_C64=${STAGING_BINDIR_NATIVE}/dspbridge/tools/cgt6x-6.0.7 \
	   DBS_FC=${STAGING_BINDIR}/dspbridge/dsp/bdsptools/framework_components_1_10_04/packages-bld \
	   DLLCREATE_DIR=${STAGING_BINDIR_NATIVE}/DLLcreate \
"

#set to release or debug
RELEASE = "release"

inherit ccasefetch

do_compile() {
	## Getting MasterConfig files
	mkdir -p ${S}/include
	cp -a ${STAGING_INCDIR}/dspbridge/include/* ${S}/include
	## Getting utils files
	mkdir -p ${S}/system/utils
	cp -a ${STAGING_BINDIR}/dspbridge/system/utils/* ${S}/system/utils
	## Getting usn files
	mkdir -p ${S}/system/usn
	cp -a ${STAGING_BINDIR}/dspbridge/system/usn/* ${S}/system/usn
	## Getting inst2 files
	mkdir -p ${S}/system/inst2
	cp -a ${STAGING_BINDIR}/dspbridge/system/inst2/* ${S}/system/inst2
	## Getting dasf files
	mkdir -p ${S}/system/dasf
	cp -a ${STAGING_BINDIR}/dspbridge/system/dasf/* ${S}/system/dasf
	## Getting the dsp make system
	mkdir -p ${S}/make
	cp -a ${STAGING_BINDIR}/dspbridge/make/* ${S}/make 
	## Setting PATH for gmake
	pathorig=$PATH
	export PATH=$PATH:${STAGING_BINDIR_NATIVE}/dspbridge/tools/xdctools
	chmod -R +w ${S}/*
	cd ${S}/video/lib/conversions
	sed -e 's%\\%\/%g' makefile > makefile.linux
	${ENV_VAR} oe_runmake -f makefile.linux build=omap3430${RELEASE}
	export PATH=$pathorig
	unset pathorig
}

do_stage() {
	install -d ${STAGING_BINDIR}/dspbridge/video/lib/conversions
	cp -a ${S}/video/lib/conversions/inc/* ${STAGING_BINDIR}/dspbridge/video/lib/conversions
}

do_install() {
	install -d ${D}${base_libdir}/dsp
	install -m 0644 ${S}/video/lib/conversions/out/omap3430/${RELEASE}/conversions.dll64P ${D}${base_libdir}/dsp
}

FILES_${PN} = "${base_libdir}/dsp"
