#
# Copyright (C) 2007 OpenedHand Ltd.
#
IMAGE_INSTALL = "task-poky-boot task-omap-bridge task-omap-omx ${ROOTFS_PKGMANAGE}"

IMAGE_LINGUAS = " "

inherit poky-image

# remove not needed ipkg informations
ROOTFS_POSTPROCESS_COMMAND += "remove_packaging_data_files"
