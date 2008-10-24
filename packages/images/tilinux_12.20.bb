# This is a Sato image
# TODO: include task-gstmmf
IMAGE_FEATURES += "apps-console-core ${SATO_IMAGE_FEATURES}"

IMAGE_INSTALL += "tidspbridge-module tidspbridge-lib tiopenmax libgoo gst-goo"

PREFERRED_PROVIDER_tidspbridge-lib = tidspbridge-lib_12.20
PREFERRED_PROVIDER_tiopenmax = tiopenmax_12.20
PREFERRED_PROVIDER_libgoo = libgoo_4.20
PREFERRED_PROVIDER_gst-goo = gst-goo_4.20

inherit poky-image
