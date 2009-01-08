#
# Copyright (C) 2008 Texas Instruments
#

DESCRIPTION = "Tasks for TI's connectivity package"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"

RDEPENDS = "\
	btfm \
	wilink \
	fmapp \
	iperf \
"
