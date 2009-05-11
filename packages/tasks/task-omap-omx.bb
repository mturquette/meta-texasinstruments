#
# Copyright (C) 2008 Texas Instruments
#

DESCRIPTION = "Tasks for TI's OpenMAX IL"
PR = "r3"

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"

# NOTE! The contents of this file should be replaced by the contents of
# meta-tiopenmax-modular.bb after the original tiopenmax_*.bb is phased out.
# Until then this file simply RDEPENDS on virtual/openmax-il.  Food for thought:
# should we keep this mechanism around to allow for other OpenMAX
# implementations?

RDEPENDS = "virtual/openmax-il"
