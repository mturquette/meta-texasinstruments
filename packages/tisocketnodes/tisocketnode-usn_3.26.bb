DESCRIPTION = "Texas Instruments USN Socket Node."
PR = "r0"
DEPENDS = "baseimage"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/system/usn/... DSP-MM-TID-SYSTEM_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/system/usn"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR =${S}/speech/node/g711/dec
#set to release or debug
RELEASE = "release"

inherit ccasefetch tisocketnode

do_compile() {
## Getting MasterConfig files
        mkdir -p ${S}/include
        cp -a ${STAGING_INCDIR}/dspbridge/include/* ${S}/include
## Getting utils files
	mkdir -p ${S}/system/utils
	cp -a ${STAGING_BINDIR}/dspbridge/system/utils/* ${S}/system/utils
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
        export PATH=$PATH:${STAGING_BINDIR}/dspbridge/tools/xdctools
        chmod -R +w ${S}/*
        cd ${SN_DIR}
	sed -e 's%\\%\/%g' makefile > makefile.linux
	${ENV_VAR} oe_runmake -f makefile.linux build=omap3430${RELEASE}
	export PATH=$pathorig
        unset pathorig
}

do_stage() {
	install -d ${STAGING_BINDIR}/dspbridge/system/usn
	cp -a ${S}/system/usn/* ${STAGING_BINDIR}/dspbridge/system/usn
}
