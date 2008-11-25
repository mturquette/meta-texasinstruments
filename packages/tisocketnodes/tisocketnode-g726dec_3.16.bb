DESCRIPTION = "Texas Instruments G726 Decoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-g726dec-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/speech/node/g726/dec/... DSP-MM-TID-AUDIO_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/speech/node/g726/dec"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/speech/node/g726/dec

#set to release or debug
RELEASE = "release"

inherit ccasefetch tisocketnode

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
        chmod -R +w ${S}/*
	cd ${SN_DIR}
	sed -e 's%\\%\/%g' makefile > makefile.linux
	${ENV_VAR} oe_runmake -f makefile.linux build=omap3430${RELEASE}
        export PATH=$pathorig
        unset pathorig
}
