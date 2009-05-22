DESCRIPTION = "Texas Instruments OpenMAX IL."
PR = "r2"
PROVIDES = "virtual/openmax-il"
RPROVIDES = "virtual/openmax-il"

ALLOW_EMPTY = "1"

RDEPENDS = "\
	tiopenmax-aacdec \
	tiopenmax-aacenc \
	${@base_contains("DISTRO_FEATURES", "720p", "tiopenmax-armaacdec", "", d)} \
	${@base_contains("DISTRO_FEATURES", "720p", "tiopenmax-armaacenc", "", d)} \
	tiopenmax-audiomanager \
	${@base_contains("DISTRO_FEATURES", "camera", "tiopenmax-camera", "", d)} \
	tiopenmax-clock \
	tiopenmax-common \
	tiopenmax-core \
	tiopenmax-g711dec \
	tiopenmax-g711enc \
	tiopenmax-g722dec \
	tiopenmax-g722enc \
	tiopenmax-g723dec \
	tiopenmax-g723enc \
	tiopenmax-g726dec \
	tiopenmax-g726enc \
	tiopenmax-g729dec \
	tiopenmax-g729enc \
	tiopenmax-gsmfrdec \
	tiopenmax-gsmfrenc \
	tiopenmax-gsmhrdec \
	tiopenmax-gsmhrenc \
	tiopenmax-ilbcdec \
	tiopenmax-ilbcenc \
	tiopenmax-imaadpcmdec \
	tiopenmax-imaadpcmenc \
	${@base_contains("DISTRO_FEATURES", "jpegdec", "tiopenmax-jpegdec", "", d)} \
	${@base_contains("DISTRO_FEATURES", "jpegenc", "tiopenmax-jpegenc", "", d)} \
	tiopenmax-lcml \
	tiopenmax-mp3 \
	tiopenmax-nbamrdec \
	tiopenmax-nbamrenc \
	tiopenmax-pcmdec \
	tiopenmax-pcmenc \
	tiopenmax-perf \
	tiopenmax-policymanager \
	tiopenmax-postproc \
	${@base_contains("DISTRO_FEATURES", "rarv", "tiopenmax-rageckodec", "", d)} \
	tiopenmax-ram \
	tiopenmax-resourcemanager \
	tiopenmax-rmproxy \
	tiopenmax-videodec \
	tiopenmax-videoenc \
	tiopenmax-wbamrdec \
	tiopenmax-wbamrenc \
	tiopenmax-wmadec \
	"

DEPENDS="${RDEPENDS}"
