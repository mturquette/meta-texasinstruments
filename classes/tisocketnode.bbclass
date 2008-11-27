DEPENDS += "baseimage \
	   tisocketnode-ringio \
	   tisocketnode-usn"

ENV_VAR = "DEPOT=${STAGING_BINDIR}/dspbridge/tools \
           MMCODEC_ROOT=${STAGING_BINDIR}/dspbridge \
	   DSPMAKEROOT=${S}/make \
	   DBS_BRIDGE_DIR_C64=${STAGING_BINDIR}/dspbridge/dsp \
	   DBS_SABIOS_DIR_C64=${STAGING_BINDIR}/dspbridge/tools \
	   DBS_CGTOOLS_DIR_C64=${STAGING_BINDIR}/dspbridge/tools/cgt6x-6.0.7 \
	   DBS_FC=${STAGING_BINDIR}/dspbridge/dsp/bdsptools/framework_components_1_10_04/packages-bld \
	   DLLCREATE_DIR=${STAGING_BINDIR}/DLLcreate \
"

RELEASE ?= "release"

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
## Getting inst2 files
        mkdir -p ${S}/system/inst2
        cp -a ${STAGING_BINDIR}/dspbridge/system/inst2/* ${S}/system/inst2
## Getting dasf files
        mkdir -p ${S}/system/dasf
        cp -a ${STAGING_BINDIR}/dspbridge/system/dasf/* ${S}/system/dasf
## Getting ringio files
	mkdir -p ${S}/system/ringio
	cp -a ${STAGING_BINDIR}/dspbridge/system/ringio/* ${S}/system/ringio
## Setting PATH for gmake
        pathorig=$PATH
        export PATH=$PATH:${STAGING_BINDIR}/dspbridge/tools/xdctools
	chmod -R +w ${S}/*	
	cd ${SN_DIR}
	sed -e 's%\\%\/%g' makefile > makefile.linux
	${ENV_VAR} oe_runmake -f makefile.linux build=omap3430${RELEASE}
        export PATH=$pathorig
        unset pathorig
}

do_install() {
	install -d ${D}${base_libdir}/dsp
	install -m 0644 ${SN_DIR}/out/omap3430/${RELEASE}/*.dll64P ${D}${base_libdir}/dsp
}

FILES_${PN}="${base_libdir}/dsp"
