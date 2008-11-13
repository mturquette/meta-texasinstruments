PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Socket Nodes"
PR = "r0"

S = ${WORKDIR}/target

SRC_URI = "http://omapssp.dal.design.ti.com/VOBS/CSSD_Linux_Releases/3430/Linux_23.x/CSSD_Linux_${PV}RC4.tar.bz2"

do_unpack() {
	cd ${WORKDIR}
	tar jxf ${DL_DIR}/CSSD_Linux_${PV}RC4.tar.bz2 CSSD_Linux_${PV}RC4/target/lib/dsp --strip-components 1
}

do_install() {
	install -d ${D}/lib/dsp
	install -m 0644 -t ${D}/lib/dsp ${S}/lib/dsp/*
}

FILES_${PN} += "\
	/lib/dsp \
	"
