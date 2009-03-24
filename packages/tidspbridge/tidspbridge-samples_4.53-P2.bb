PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU/DSP Bridge Socket Node compilation."
LICENSE = "LGPL"
PR = "r1"
DEPENDS = "tidspbridge-bios-native \
	   tidspbridge-cgt6x-native \
	   tidspbridge-dllcreate-native"
FILES_${PN}="/dspbridge"

CCASE_SPEC = "%\
	      element /vobs/SDS/Source/Bridge/dsp/... BRIDGE-DSP_RLS_${PV}%\
	      element /vobs/SDS/Source/Bridge/dsp/... -error%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/SDS/Source/Bridge/dsp"
CCASE_PATHCOMPONENT = "dsp"
CCASE_PATHCOMPONENTS = "4"

ENV_VAR = "DEPOT=${STAGING_BINDIR_NATIVE}/dspbridge/tools \
	   DBS_BRIDGE_DIR_C64=${S} \
	   DBS_SABIOS_DIR_C64=${STAGING_BINDIR_NATIVE}/dspbridge/tools \
	   DBS_CGTOOLS_DIR_C64=${STAGING_BINDIR_NATIVE}/dspbridge/tools/cgt6x-6.0.7 \
	   DBS_FC=${S}/bdsptools/framework_components_1_10_04/packages-bld \
	   DLLCREATE_DIR=${STAGING_BINDIR_NATIVE}/DLLcreate \
"

SRC_URI = "file://tidspbridge.patch;patch=1"

inherit ccasefetch

do_compile() {
	chmod -R +w ${S}/*
	${ENV_VAR} oe_runmake -f gmakefile .clean
	${ENV_VAR} oe_runmake -f gmakefile .bridge_samples
}

do_stage() {
	install -d ${STAGING_BINDIR}/dspbridge/dsp
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/dsp
	install -d ${STAGING_LIBDIR}/dspbridge/exports/lib
	install -m 0644 ${S}/ti/dspbridge/dsp/bridge_product/exports/lib/*.a64P ${STAGING_LIBDIR}/dspbridge/exports/lib
	install -d ${STAGING_INCDIR}/dspbridge/exports/include
	install -m 0644 ${S}/ti/dspbridge/dsp/bridge_product/exports/include/*.h ${STAGING_INCDIR}/dspbridge/exports/include
}

do_install() {
	install -d ${D}/dspbridge/exports/lib
	install -m 0644 ${S}/ti/dspbridge/dsp/bridge_product/exports/lib/*.a64P ${D}/dspbridge/exports/lib
	install -m 0644 ${S}/ti/dspbridge/dsp/samples/*.dof64P ${D}/dspbridge
	install -m 0644 ${S}/ti/dspbridge/dsp/qos/*.dll64P ${D}/dspbridge
}
