#
# Copyright (C) 2008 Texas Instruments
#

DESCRIPTION = "Tasks for TI's dspbridge"
PR = "r1"

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"

RDEPENDS = "\
    tidspbridge-module \
    tidspbridge-lib \
    tidspbridge-samples \
    tidspbridge-extra \
    baseimage \
    "
