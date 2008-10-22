#
# Copyright (C) 2008 Texas Instruments
#

DESCRIPTION = "Tasks for the GStreamer Multimedia Framework"
PR = "r1"

PACKAGES = "\
    task-gstmmf-libs \
    task-gstmmf-plugins \
    task-gstmmf-apps \
    "

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"

RDEPENDS_task-gstmmf-libs = "\
    libid3tag	\
    libmad	\
    liboil	\
    libxml2	\
    popt	\
    check	\
    gstreamer	\
    libgoo	\
    "

RDEPENDS_task-gstmmf-plugins = "\
    gst-plugins-base	\
    gst-plugins-good	\
    gst-plugins-bad	\
    gst-plugins-ugly	\
    gst-ffmpeg		\
    gst-goo		\
    "

RDEPENDS_task-gstmmf-apps = ""
