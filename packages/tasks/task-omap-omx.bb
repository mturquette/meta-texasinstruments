#
# Copyright (C) 2008 Texas Instruments
#

DESCRIPTION = "Tasks for the TI's OpenMAX IL"
PR = "r1"

PACKAGES = "\
    task-omap-omx \
    task-omap-omx-libs \
    task-omap-omx-apps \
    "

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"

RDEPENDS_task-omap-omx = "\
    task-omap-omx-libs \
    task-omap-omx-apps \
    "

RDEPENDS_task-omap-omx-libs = "\
    tiopenmax \
    "

RDEPENDS_task-omap-omx-apps = ""
