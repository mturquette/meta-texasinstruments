# This is based on Sato image, plus TI stuff
IMAGE_FEATURES += "apps-console-core omap-bridge omap-omx ${SATO_IMAGE_FEATURES}"

PREFERRED_VERSION_tidspbridge-module = "12.20"
PREFERRED_VERSION_tidspbridge-lib =    "12.20"
PREFERRED_VERSION_tiopenmax =          "12.20"
PREFERRED_VERSION_libgoo =             "4.20"
PREFERRED_VERSION_gst-goo =            "4.20"

inherit omap-image
