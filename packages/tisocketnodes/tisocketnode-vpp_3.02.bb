DESCRIPTION = "Texas Instruments VPP Socket Node."
PR = "r0"
DEPENDS += "baseimage-vgpop"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/video/node/vpp/... DSP-MM-TII-MM_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/video/node/vpp"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

#set to release or debug
RELEASE = "release"

inherit ccasefetch tisocketnode

SN_DIR=${S}/video/node/vpp

do_compile_prepend() {
	## Getting VGPOP files
	mkdir -p ${S}/video/alg/vgpop
	cp -a ${STAGING_BINDIR}/dspbridge/video/alg/vgpop/* ${S}/video/alg/vgpop
	## Getting SRC files
        mkdir -p ${S}/audio/alg/SampleRateConverter
        cp -a ${STAGING_BINDIR}/dspbridge/audio/alg/SampleRateConverter/* ${S}/audio/alg/SampleRateConverter
}

addtask compile_prepend after do_patch before do_compile
