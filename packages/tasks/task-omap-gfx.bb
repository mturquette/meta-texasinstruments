#
# Copyright (C) 2008 Texas Instruments
#

DESCRIPTION = "Tasks for the TI's GFX package"
PR = "r1"

RDEPENDS = "\
	sgx-kernel-module \
	sgx-lib-noxws \
    "

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"
