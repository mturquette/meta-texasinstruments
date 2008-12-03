DESCRIPTION = "Flex is a tool for generating programs that recognize lexical patterns in text."
SECTION = "devel"
LICENSE = "BSD"
require flex.inc
PR = "r0"

do_stage() {
	oe_libinstall -a libfl ${STAGING_LIBDIR}
}
