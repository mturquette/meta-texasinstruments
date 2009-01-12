# These distro features change the build options of some components
# across the TI Linux distribution.
#
# Possible features are:
# * 720p	- Enable 720p OpenMAX components, kernel.
# * gfx		- Include 3D GFX module and libraries.
# * tiwifi	- Build kernel with WLAN support.

DISTRO_FEATURES += "720p gfx tiwifi"


# This is a multimedia-based distribution. We can specify what
# codecs to enable in this release.

DISTRO_FEATURES += "camera jpegdec jpegenc"
