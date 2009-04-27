#
# Copyright (C) 2008 Texas Instruments
#

DESCRIPTION = "Tasks for TI's OpenMAX IL"
PR = "r2"

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
	tiopenmax-aacdec \
	tiopenmax-aacenc \
	tiopenmax-armaacdec \
	tiopenmax-armaacenc \
	tiopenmax-clock \
	tiopenmax-common \
	tiopenmax-core \
	tiopenmax-g711dec \
	tiopenmax-g711enc \
	tiopenmax-g722dec \
	tiopenmax-g722enc \
	tiopenmax-g723dec \
	tiopenmax-g723enc \
	tiopenmax-g726dec \
	tiopenmax-g726enc \
	tiopenmax-g729dec \
	tiopenmax-g729enc \
	tiopenmax-gsmfrdec \
	tiopenmax-gsmfrenc \
	tiopenmax-gsmhrdec \
	tiopenmax-gsmhrenc \
	tiopenmax-ilbcdec \
	tiopenmax-ilbcenc \
	tiopenmax-imaadpcmdec \
	tiopenmax-imaadpcmenc \
	tiopenmax-jpegdec \
	tiopenmax-jpegenc \
	tiopenmax-lcml \
	tiopenmax-mp3 \
	tiopenmax-nbamrdec \
	tiopenmax-nbamrenc \
	tiopenmax-pcmdec \
	tiopenmax-pcmenc \
	tiopenmax-perf \
	tiopenmax-rageckodec \
	tiopenmax-ram \
	tiopenmax-rmproxy \
	tiopenmax-wbamrdec \
	tiopenmax-wbamrenc \
	tiopenmax-wmadec \
	"

RDEPENDS_task-omap-omx-apps = "\
	tiopenmax-audiomanager \
	tiopenmax-policymanager \
	tiopenmax-resourcemanager \
	"
