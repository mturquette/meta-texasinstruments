DESCRIPTION = "Texas Instruments OpenMAX IL."
PR = "r0"
PROVIDES = "virtual/openmax-il"
DEPENDS += "\
	tiopenmax-core \
	tiopenmax-clock \
	tiopenmax-perf \
	tiopenmax-lcml \
	tiopenmax-policymanager \
	tiopenmax-resourcemanager \
	tiopenmax-audiomanager \
	"

DEPENDS += "\
	${@base_contains("DISTRO_FEATURES", "jpegdec", "tiopenmax-jpegdec", "", d)} \
	${@base_contains("DISTRO_FEATURES", "jpegenc", "tiopenmax-jpegenc", "", d)} \
	"
