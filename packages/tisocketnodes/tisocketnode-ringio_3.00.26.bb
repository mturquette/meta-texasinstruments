DESCRIPTION = "Texas Instruments RingIO Socket Node."
PR = "r0"
DEPENDS = "baseimage"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/system/ringio/... DSP-MM-TII-SYSTEM_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/system/ringio"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/system/ringio

#set to release or debug
RELEASE = "release"

inherit ccasefetch tisocketnode

do_compile() {
## Getting the Master Config files
	mkdir -p ${S}/include 
        cp -a ${STAGING_INCDIR}/dspbridge/include/* ${S}/include
## Getting the make system
	mkdir -p ${S}/make
        cp -a ${STAGING_BINDIR}/dspbridge/make/* ${S}/make
## Setting path to find gmake
        pathorig=$PATH
	export PATH=$PATH:${STAGING_BINDIR}/dspbridge/tools/xdctools
        chmod -R +w ${S}/*
	cd ${SN_DIR}
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
