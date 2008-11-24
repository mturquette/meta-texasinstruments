PRIORITY = "optional"
DESCRIPTION = "Texas Instruments IMA-ADPCM Decoder Socket Node."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "baseimage \
	   tisocketnode-ringio \
	   tisocketnode-usn \
	   tisocketnode-ima-adpcm-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/audio/node/ima-adpcm/dec/... DSP-MM-TID-AUDIO_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/audio/node/ima-adpcm/dec"
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
## Getting usn files
        mkdir -p ${S}/system/usn
        cp -a ${STAGING_BINDIR}/dspbridge/system/usn/* ${S}/system/usn
## Getting dasf files
        mkdir -p ${S}/system/dasf
        cp -a ${STAGING_BINDIR}/dspbridge/system/dasf/* ${S}/system/dasf
## Setting PATH for gmake
        pathorig=$PATH
        export PATH=$PATH:${STAGING_BINDIR}/dspbridge/tools/xdctools
	cd ${S}/audio/node/ima-adpcm/dec
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
	install -d ${D}${libdir}/dsp
	install -m 0644 ${S}/audio/node/ima-adpcm/dec/out/omap3430/${RELEASE}/ima_adpcm_dec_sn.dll64P ${D}${libdir}/dsp
#	install -m 0644 ${S}/system/baseimage/out/omap3430/${RELEASE}/baseimage.map ${D}${libdir}/dsp
}