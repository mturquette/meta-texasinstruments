DESCRIPTION = "Texas Instruments VPP Socket Node."
PR = "r0"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/video/node/vpp/... DSP-MM-TID-VIDEO_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/video/node/vpp"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/video/node/vpp

#set to release or debug
RELEASE = "release"

inherit ccasefetch tisocketnode

do_compile() {
## Getting utils files
	mkdir -p ${S}/system/utils
	cp -a ${STAGING_BINDIR}/dspbridge/system/utils/* ${S}/system/utils
## Getting usn files
	mkdir -p ${S}/system/usn
	cp -a ${STAGING_BINDIR}/dspbridge/system/usn/* ${S}/system/usn
## Getting inst2 files
	mkdir -p ${S}/system/inst2
        cp -a ${STAGING_BINDIR}/dspbridge/system/inst2/* ${S}/system/inst2
## Getting vgpop files
	mkdir -p ${S}/video/alg/vgpop
	cp -a ${STAGING_BINDIR}/dspbridge/video/alg/vgpop/* ${S}/video/alg/vgpop
## Getting MasterConfig files
        mkdir -p ${S}/include
        cp -a ${STAGING_INCDIR}/dspbridge/include/* ${S}/include
        mkdir -p ${S}/audio/alg/SampleRateConverter
        cp -a ${STAGING_BINDIR}/dspbridge/audio/alg/SampleRateConverter/* ${S}/audio/alg/SampleRateConverter
## Getting the dsp make system
        mkdir -p ${S}/make
        cp -a ${STAGING_BINDIR}/dspbridge/make/* ${S}/make
        chmod -R +w ${S}/make
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
