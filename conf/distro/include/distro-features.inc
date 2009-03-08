# These distro features change the build options of some components
# across the TI Linux distribution.
#
# Possible features are:
# * 720p	- Enable 720p OpenMAX components, kernel.
# * gfx		- Include 3D GFX module and libraries.
# * tiwifi	- Include support for TI's WLAN stack.
# * tibluetooth	- Include support for TI's Bluetooth and FM stack.

#DISTRO_FEATURES += "gfx tiwifi tibluetooth"


# This is a multimedia-based distribution. We can specify what
# codecs to enable in this release.

DISTRO_FEATURES += "camera jpegdec jpegenc tiwifi tibluetooth gfx 720p"
