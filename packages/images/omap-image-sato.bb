# This is based on Sato image, plus TI stuff
IMAGE_FEATURES += "apps-console-core ${SATO_IMAGE_FEATURES}"
IMAGE_FEATURES += "omap-bridge"
#IMAGE_FEATURES += "omap-sn"
IMAGE_FEATURES += "omap-omx"
IMAGE_FEATURES += "omap-gst"
#IMAGE_FEATURES += "omap-gfx"
#IMAGE_FEATURES += "omap-conn"
#IMAGE_FEATURES += "omap-test"
inherit omap-image
