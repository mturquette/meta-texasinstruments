# This is based on Sato image, plus TI stuff
IMAGE_FEATURES += "apps-console-core omap-bridge omap-omx omap-gst ${SATO_IMAGE_FEATURES}"

PREFERRED_VERSION_tidspbridge-module = "23.10"
PREFERRED_VERSION_tidspbridge-lib =    "23.10"
PREFERRED_VERSION_tidspbridge-extra =  "23.10"
PREFERRED_VERSION_tisocketnodes =      "23.10"
PREFERRED_VERSION_tiopenmax =          "12.20"
PREFERRED_VERSION_libgoo =             "5.10"
PREFERRED_VERSION_gst-goo =            "5.10"

inherit omap-image
