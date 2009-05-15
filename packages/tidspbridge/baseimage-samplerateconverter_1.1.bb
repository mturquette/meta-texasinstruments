PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Baseimage Audio Algorithm SampleRateConverter."
LICENSE = "LGPL"
PR = "r0"

require baseimage-dasf-cspec-${PV}.inc

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/audio/alg/SampleRateConverter"
CCASE_PATHCOMPONENT = "SampleRateConverter"
CCASE_PATHCOMPONENTS = "5"

inherit ccasefetch

do_compile() {
}

do_stage() {
        chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/audio/alg/SampleRateConverter
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/audio/alg/SampleRateConverter
}
