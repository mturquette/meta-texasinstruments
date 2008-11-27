#
# Copyright (C) 2008 Texas Instruments
#

DESCRIPTION = "Tasks for TI's Socket Nodes"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"

RDEPENDS = "\
    tisocketnode-control-task \
    tisocketnode-gsmhrdec \
    tisocketnode-mpeg4dec \
    tisocketnode-dfgm \
    tisocketnode-g711dec \
    tisocketnode-gsmhrenc \
    tisocketnode-mpeg4enc \
    tisocketnode-h264dec \
    tisocketnode-nbamrdec \
    tisocketnode-g711enc \
    tisocketnode-h264enc \
    tisocketnode-nbamrenc \
    tisocketnode-g722dec \
    tisocketnode-ilbcdec \
    tisocketnode-pcmdec \
    tisocketnode-g722enc \
    tisocketnode-pcmenc \
    tisocketnode-ilbcenc \
    tisocketnode-ringio \
    tisocketnode-g723dec \
    tisocketnode-ima-adpcmdec \
    tisocketnode-ima-adpcmenc \
    tisocketnode-g723enc \
    tisocketnode-spark \
    tisocketnode-ipp \
    tisocketnode-g726dec \
    tisocketnode-jpegdec \
    tisocketnode-usn \
    tisocketnode-vpp \
    tisocketnode-g726enc \
    tisocketnode-jpegenc \
    tisocketnode-wbamrdec \
    tisocketnode-g729dec \
    tisocketnode-mp3 \
    tisocketnode-wbamrenc \
    tisocketnode-g729enc \
    tisocketnode-mpeg2 \
    tisocketnode-wma9 \
    tisocketnode-gsmfrdec \
#    tisocketnode-mpeg4aacdec \
    tisocketnode-wmv9 \
    tisocketnode-gsmfrenc \
#    tisocketnode-mpeg4aacenc \
    "

DEPENDS = "\
    tisocketnode-algo \
    tisocketnode-nmu-plc-vad \
    "
