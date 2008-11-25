DESCRIPTION = "Texas Instruments IPP Socket Node."
PR = "r0"
DEPENDS = "baseimage \
	   tisocketnode-algo \
	   tisocketnode-ringio \
	   tisocketnode-usn \
	   tisocketnode-dfgm"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/ivp/... DSP-MM-TID-IMVID_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/ivp"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/ivp/node/ipp

#set to release or debug
RELEASE = "release"

inherit ccasefetch tisocketnode

do_compile() {
## Getting MasterConfig files
        mkdir -p ${S}/include
        cp -a ${STAGING_INCDIR}/dspbridge/include/* ${S}/include
## Getting dsp make system
        mkdir -p ${S}/make
        cp -a ${STAGING_BINDIR}/dspbridge/make/* ${S}/make
        chmod -R +w ${S}/make
## Getting utils files
        mkdir -p ${S}/system/utils
        cp -a ${STAGING_BINDIR}/dspbridge/system/utils/* ${S}/system/utils
## Getting inst2 files
        mkdir -p ${S}/system/inst2
        cp -a ${STAGING_BINDIR}/dspbridge/system/inst2/* ${S}/system/inst2
## Getting algo files
	mkdir -p ${S}/algo
	cp -a ${STAGING_BINDIR}/dspbridge/algo/* ${S}/algo
## Getting dfgm files
	mkdir -p ${S}/system/dfgm
	cp -a ${STAGING_BINDIR}/dspbridge/system/dfgm/* ${S}/system/dfgm
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
	install -m 0644 ${S}/ivp/node/ipp/out/omap3430/${RELEASE}/ipp_sn.dll64P ${D}${base_libdir}/dsp
	install -m 0644 ${S}/ivp/alg/star/lib/omap3430/release/star.l64P ${D}${base_libdir}/dsp
	install -m 0644 ${S}/algo/ivp/chromasuppress/lib/chromasuppress.l64p ${D}${base_libdir}/dsp
	install -m 0644 ${S}/algo/ivp/edgeenhance/lib/eenf_ti.l64P ${D}${base_libdir}/dsp
	install -m 0644 ${S}/algo/ivp/yuvconvert/lib/yuvconvert.l64p ${D}${base_libdir}/dsp
}
