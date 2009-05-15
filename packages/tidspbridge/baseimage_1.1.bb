PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Baseimage."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "baseimage-avsync \
   baseimage-dasf \
   baseimage-hal \
   baseimage-inst2 \
   baseimage-samplerateconverter \
   baseimage-tmon \
   baseimage-vgpop \
   baseimage-make-system \
   baseimage-utils \
   baseimage-masterconfig \
   tidspbridge-samples"
FILES_${PN}="${base_libdir}/dsp/baseimage.dof"

require baseimage-system-cspec-${PV}.inc

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/system"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

ENV_VAR = "DEPOT=${STAGING_BINDIR_NATIVE}/dspbridge/tools \
   DSPMAKEROOT=${S}/make \
   DBS_BRIDGE_DIR_C64=${STAGING_BINDIR}/dspbridge/dsp \
   DBS_SABIOS_DIR_C64=${STAGING_BINDIR_NATIVE}/dspbridge/tools \
   DBS_CGTOOLS_DIR_C64=${STAGING_BINDIR_NATIVE}/dspbridge/tools/cgt6x-6.0.7 \
   DBS_FC=${STAGING_BINDIR}/dspbridge/dsp/bdsptools/framework_components_1_10_04/packages-bld \
"

#set to release or debug
RELEASE = "release"

inherit ccasefetch

do_compile() {
  pwd
## Getting system files
  cd ${S}/system/baseimage
  cp -fa ${STAGING_BINDIR}/dspbridge/system/utils/* ${S}/system/utils
  cp -fa ${STAGING_BINDIR}/dspbridge/system/inst2/* ${S}/system/inst2
  cp -fa ${STAGING_BINDIR}/dspbridge/system/avsync/* ${S}/system/avsync
  cp -fa ${STAGING_BINDIR}/dspbridge/system/dasf/* ${S}/system/dasf
  cp -fa ${STAGING_BINDIR}/dspbridge/system/hal/* ${S}/system/hal
  cp -fa ${STAGING_BINDIR}/dspbridge/system/tmon/* ${S}/system/tmon
## Getting vgpop files
  mkdir -p ${S}/video/alg/vgpop
  cp -a ${STAGING_BINDIR}/dspbridge/video/alg/vgpop/* ${S}/video/alg/vgpop
## Getting MasterConfig files
  mkdir -p ${S}/include
  cp -a ${STAGING_INCDIR}/dspbridge/include/* ${S}/include
## Getting SampleRateConverte files
  mkdir -p ${S}/audio/alg/SampleRateConverter
  cp -a ${STAGING_BINDIR}/dspbridge/audio/alg/SampleRateConverter/* ${S}/audio/alg/SampleRateConverter
  sed -e 's%\\%\/%g' makefile > makefile.linux
## Getting the dsp make system
  mkdir -p ${S}/make
  cp -a ${STAGING_BINDIR}/dspbridge/make/* ${S}/make
## Setting PATH for gmake
  pathorig=$PATH
  export PATH=$PATH:${STAGING_BINDIR_NATIVE}/dspbridge/tools/xdctools
  ${ENV_VAR} oe_runmake -f makefile.linux build=omap3430${RELEASE}
  export PATH=$pathorig
  unset pathorig
}

#do_stage() {
#  install -d ${STAGING_LIBDIR}/dspbridge/exports/lib
#  install -m 0644 ${S}/ti/dspbridge/dsp/bridge_product/exports/lib/*.a64P ${STAGING_LIBDIR}/dspbridge/exports/lib
#  install -d ${STAGING_INCDIR}/dspbridge/exports/include
#  install -m 0644 ${S}/ti/dspbridge/dsp/bridge_product/exports/include/*.h ${STAGING_INCDIR}/dspbridge/exports/include
#}

do_install() {
  install -d ${D}${base_libdir}/dsp
  install -m 0644 ${S}/system/baseimage/out/omap3430/${RELEASE}/baseimage.dof ${D}${base_libdir}/dsp
  install -m 0644 ${S}/system/baseimage/out/omap3430/${RELEASE}/baseimage.map ${D}${base_libdir}/dsp
}
