DESCRIPTION = "Texas Instruments IPP Socket Node."
PR = "r1"
DEPENDS += "tisocketnode-algo tisocketnode-dfgm"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/ivp/... DSP-MM-TII-MM_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/ivp"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

#set to release or debug
RELEASE = "release"

inherit ccasefetch tisocketnode

SN_DIR=${S}/ivp/node/ipp

do_compile_prepend() {
	## Getting algo files
	mkdir -p ${S}/algo
	cp -a ${STAGING_BINDIR}/dspbridge/algo/* ${S}/algo
	## Getting dfgm files
	mkdir -p ${S}/system/dfgm
	cp -a ${STAGING_BINDIR}/dspbridge/system/dfgm/* ${S}/system/dfgm
}

do_install() {
	install -d ${D}${base_libdir}/dsp
	install -m 0644 ${S}/ivp/node/ipp/out/omap3430/${RELEASE}/ipp_sn.dll64P ${D}${base_libdir}/dsp
	install -m 0644 ${S}/ivp/alg/star/lib/omap3430/release/star.l64P ${D}${base_libdir}/dsp
	install -m 0644 ${S}/algo/ivp/chromasuppress/lib/chromasuppress.l64p ${D}${base_libdir}/dsp
	install -m 0644 ${S}/algo/ivp/edgeenhance/lib/eenf_ti.l64P ${D}${base_libdir}/dsp
	install -m 0644 ${S}/algo/ivp/yuvconvert/lib/yuvconvert.l64p ${D}${base_libdir}/dsp
}

addtask compile_prepend after do_patch before do_compile
