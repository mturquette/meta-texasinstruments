#
# Copyright (C) 2008 Texas Instruments
#

DESCRIPTION = "Tasks for the TI's GFX package"
PR = "r1"

PACKAGES = "\
    task-omap-gfx \
	task-omap-gfx-xorg \
    task-omap-gfx-apps \
    "

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"

RDEPENDS_task-omap-gfx = "\
	sgx-pvr-module \
	"
#	sgx-pvr-lib \
#   "

RDEPENDS_task-omap-gfx-apps = ""

RDEPENDS_task-omap-gfx-xorg = ""
