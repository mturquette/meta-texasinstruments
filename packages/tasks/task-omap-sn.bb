#
# Copyright (C) 2008 Texas Instruments
#

DESCRIPTION = "Tasks for TI's Socket Nodes"
PR = "r2"

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"

RDEPENDS = "\
    tisocketnode-control-task \
    tisocketnode-dfgm \
    tisocketnode-ipp \
    tisocketnode-ringio \
    tisocketnode-usn \
    \
    tisocketnode-g711dec \
    tisocketnode-g711enc \
    tisocketnode-g722dec \
    tisocketnode-g722enc \
    tisocketnode-g723dec \
    tisocketnode-g723enc \
    tisocketnode-g726dec \
    tisocketnode-g726enc \
    tisocketnode-g729dec \
    tisocketnode-g729enc \
    tisocketnode-gsmfrdec \
    tisocketnode-gsmfrenc \
    tisocketnode-gsmhrdec \
    tisocketnode-gsmhrenc \
    tisocketnode-ilbcdec \
    tisocketnode-ilbcenc \
    tisocketnode-ima-adpcmdec \
    tisocketnode-ima-adpcmenc \
    tisocketnode-mp3 \
    tisocketnode-mpeg4aacdec \
    tisocketnode-mpeg4aacenc \
    tisocketnode-nbamrdec \
    tisocketnode-nbamrenc \
    tisocketnode-pcmdec \
    tisocketnode-pcmenc \
    tisocketnode-wbamrdec \
    tisocketnode-wbamrenc \
    tisocketnode-wma9 \
    \
    tisocketnode-h264dec \
    tisocketnode-h264enc \
    tisocketnode-mpeg2 \
    tisocketnode-mpeg4dec \
    tisocketnode-mpeg4enc \
    tisocketnode-spark \
    tisocketnode-vpp \
    tisocketnode-wmv9 \
    ${@base_contains("DISTRO_FEATURES", "720p", "tisocketnode-mpeg4-720pdec", "", d)} \
    ${@base_contains("DISTRO_FEATURES", "720p", "tisocketnode-mpeg4-720penc", "", d)} \
    \
    tisocketnode-jpegdec \
    tisocketnode-jpegenc \
    \
    ${@base_contains("DISTRO_FEATURES", "rarv", "tisocketnode-rageckodec", "", d)} \
    ${@base_contains("DISTRO_FEATURES", "rarv", "tisocketnode-rv89combodec", "", d)} \
    "

DEPENDS = "\
    tisocketnode-algo \
    tisocketnode-nmu-plc-vad \
    "
