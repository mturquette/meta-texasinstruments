DESCRIPTION = "GNU Project parser generator (yacc replacement)."
HOMEPAGE = "http://www.gnu.org/software/bison/"
LICENSE = "GPL"
SECTION = "devel"
PRIORITY = "optional"

SRC_URI = "${GNU_MIRROR}/bison/bison-${PV}.tar.gz"

S = ${WORKDIR}/bison-1.875

EXTRA_OECONF = " --program-suffix=-${PV} --prefix=${STAGING_DIR_TARGET}/usr/local"

do_stage() {
	install -d ${STAGING_DIR_TARGET}/usr/local/bin ${STAGING_DIR_TARGET}/usr/local/share \
		${STAGING_DIR_TARGET}/usr/local/info ${STAGING_DIR_TARGET}/usr/local/lib ${STAGING_DIR_TARGET}/usr/local/man
	rm -f ${STAGING_DIR_TARGET}/usr/local/bin/yacc-${PV}
	rm -f ${STAGING_DIR_TARGET}/usr/local/bin/${PN}
	install -m 0755 src/bison ${STAGING_DIR_TARGET}/usr/local/bin/${PN}
	cat >${STAGING_DIR_TARGET}/usr/local/bin/yacc-${PV} <<EOF
#!/bin/sh
exec ${STAGING_DIR_TARGET}/usr/local/bin/${PN} -y "\$@"
EOF
	chmod a+rx ${STAGING_DIR_TARGET}/usr/local/bin/yacc-${PV}
	install -d ${STAGING_DIR_TARGET}/usr/local/share/bison/m4sugar
	install -m 0755 data/c.m4 data/glr.c data/lalr1.cc data/yacc.c ${STAGING_DIR_TARGET}/usr/local/share/bison
	install -m 0755 data/m4sugar/m4sugar.m4 ${STAGING_DIR_TARGET}/usr/local/share/bison/m4sugar/
}

inherit autotools
