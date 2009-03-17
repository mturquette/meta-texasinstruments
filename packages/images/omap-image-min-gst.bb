#
# Copyright (C) 2007 OpenedHand Ltd.
#
IMAGE_INSTALL = "\
	${ROOTFS_PKGMANAGE} \
	task-poky-boot \
	task-omap-bridge \
	task-omap-omx \
	task-omap-gst \
	"

IMAGE_LINGUAS = " "

inherit poky-image

# remove not needed ipkg informations
ROOTFS_POSTPROCESS_COMMAND += "remove_packaging_data_files"
