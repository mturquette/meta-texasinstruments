SECTION = "utils"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments OMAP Support Tools"
LICENSE = "GPL"
PR="r0"
DEPENDS = "perl-native"

PACKAGE_ARCH = ${HOST_ARCH}

inherit native

SRC_URI = "git://git.omapzoom.org/repo/support-tools.git;branch=master;protocol=http"
SRCREV = "bc792eb7daa29fe73b76da515ada4a680522e6fc"

S = ${WORKDIR}/git

do_stage () {
	install -d ${STAGING_BINDIR}
	install -m 755 ${S}/boot/omap3430gp-signer.pl ${STAGING_BINDIR}
}
