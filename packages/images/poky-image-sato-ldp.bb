inherit poky-image

IMAGE_FEATURES += "apps-console-core ${SATO_IMAGE_FEATURES}"
IMAGE_INSTALL += "tidspbridge tiopenmax libgoo gst-goo"
