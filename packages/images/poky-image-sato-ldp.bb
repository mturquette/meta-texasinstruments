IMAGE_FEATURES += "apps-console-core ${SATO_IMAGE_FEATURES}"
IMAGE_INSTALL += "tidspbridge-module tidspbridge-lib "
#IMAGE_INSTALL += "tiopenmax libgoo gst-goo"

inherit poky-image
