#
# Copyright (C) 2008 Texas Instruments
#

DESCRIPTION = "Tasks for testing software releases"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"

RDEPENDS = "\
    gst-pyapps \
    wpa-supplicant \
    iperf \
    "
