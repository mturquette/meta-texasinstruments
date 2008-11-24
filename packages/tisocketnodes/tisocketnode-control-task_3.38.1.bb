PRIORITY = "optional"
DESCRIPTION = "Texas Instruments DASF Control Task Node Node."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "baseimage"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/system/dasf/... DSP-MM-TIF-DASF_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/system/dasf"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

ENV_VAR = "DEPOT=${STAGING_BINDIR}/dspbridge/tools \
	   MMCODEC_ROOT=${STAGING_BINDIR}/dspbridge \
	   DSPMAKEROOT=${S}/make \
	   DBS_BRIDGE_DIR_C64=${STAGING_BINDIR}/dspbridge/dsp \
	   DBS_SABIOS_DIR_C64=${STAGING_BINDIR}/dspbridge/tools \
	   DBS_CGTOOLS_DIR_C64=${STAGING_BINDIR}/dspbridge/tools/cgt6x-6.0.7 \
	   DBS_FC=${STAGING_BINDIR}/dspbridge/dsp/bdsptools/framework_components_1_10_04/packages-bld \
	   DLLCREATE_DIR=${STAGING_BINDIR}/DLLcreate \
"

#set to release or debug
RELEASE = "release"

inherit ccasefetch

do_compile() {
## Getting MasterConfig files
        mkdir -p ${S}/include
        cp -a ${STAGING_INCDIR}/dspbridge/include/* ${S}/include
## Getting the dsp make system
        mkdir -p ${S}/make
        cp -a ${STAGING_BINDIR}/dspbridge/make/* ${S}/make
## Getting utils files
        mkdir -p ${S}/system/utils
        cp -a ${STAGING_BINDIR}/dspbridge/system/utils/* ${S}/system/utils
## Getting SampleRateConverte files
        mkdir -p ${S}/audio/alg/SampleRateConverter
        cp -a ${STAGING_BINDIR}/dspbridge/audio/alg/SampleRateConverter/* ${S}/audio/alg/SampleRateConverter
## Getting hal files
	mkdir -p ${S}/system/hal
        cp -a ${STAGING_BINDIR}/dspbridge/system/hal/* ${S}/system/hal
## Getting tmon files
	mkdir -p ${S}/system/tmon
        cp -a ${STAGING_BINDIR}/dspbridge/system/tmon/* ${S}/system/tmon
## Getting inst2 files
        mkdir -p ${S}/system/inst2
        cp -a ${STAGING_BINDIR}/dspbridge/system/inst2/* ${S}/system/inst2
## Setting PATH for gmake
        pathorig=$PATH
        export PATH=$PATH:${STAGING_BINDIR}/dspbridge/tools/xdctools
	cd ${S}/system/dasf/ControlTaskNode
	sed -e 's%\\%\/%g' makefile > makefile.linux
	${ENV_VAR} oe_runmake -f makefile.linux build=omap3430${RELEASE}
        export PATH=$pathorig
        unset pathorig
}

#do_stage() {
#	install -d ${STAGING_LIBDIR}/dspbridge/exports/lib
#	install -m 0644 ${S}/ti/dspbridge/dsp/bridge_product/exports/lib/*.a64P ${STAGING_LIBDIR}/dspbridge/exports/lib
#	install -d ${STAGING_INCDIR}/dspbridge/exports/include
#	install -m 0644 ${S}/ti/dspbridge/dsp/bridge_product/exports/include/*.h ${STAGING_INCDIR}/dspbridge/exports/include
#}

do_install() {
	install -d ${D}${base_libdir}/dsp
	install -m 0644 ${S}/system/dasf/ControlTaskNode/out/omap3430/${RELEASE}/dctn_dyn.dll64P ${D}${base_libdir}/dsp
#	install -m 0644 ${S}/system/baseimage/out/omap3430/${RELEASE}/baseimage.map ${D}${libdir}/dsp
}
