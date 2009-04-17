require bluez-libs.inc


S = "${WORKDIR}/bluez-libs-${PV}"
STAGING_ARM_DIR = "${STAGING_DIR}/armv7a-none-linux-gnueabi"

do_install_prepend(){

	# Copying libsdp.a to build libbtctl
        install -m 0755 ${S}/src/.libs/libbluetooth.a  ${STAGING_ARM_DIR}/usr/lib/libsdp.a
}
