PRIORITY = "optional"
DESCRIPTION = "Texas Instruments G711 Encoder Socket Node."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "baseimage \
	   tisocketnode-ringio \
	   tisocketnode-usn \
	   tisocketnode-g711enc-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/speech/node/g711/enc/... DSP-MM-TID-AUDIO_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/speech/node/g711/enc"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

ENV_VAR = "DEPOT=${STAGING_BINDIR}/dspbridge/tools \
	   DSPMAKEROOT=${STAGING_BINDIR}/dspbridge/make \
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
	cd ${S}/speech/node/g711/enc
	sed -e 's%\\%\/%g' makefile > makefile.linux
	${ENV_VAR} oe_runmake -f makefile.linux build=omap3430${RELEASE}
}

#do_stage() {
#	install -d ${STAGING_LIBDIR}/dspbridge/exports/lib
#	install -m 0644 ${S}/ti/dspbridge/dsp/bridge_product/exports/lib/*.a64P ${STAGING_LIBDIR}/dspbridge/exports/lib
#	install -d ${STAGING_INCDIR}/dspbridge/exports/include
#	install -m 0644 ${S}/ti/dspbridge/dsp/bridge_product/exports/include/*.h ${STAGING_INCDIR}/dspbridge/exports/include
#}

do_install() {
	install -d ${D}${base_libdir}/dsp
	install -m 0644 ${S}/speech/node/g711/dec/out/omap3430/${RELEASE}/g711dec_sn.dll64P ${D}${base_libdir}/dsp
#	install -m 0644 ${S}/system/baseimage/out/omap3430/${RELEASE}/baseimage.map ${D}${libdir}/dsp
}
