DESCRIPTION = "FM radio test app"
SECTION = "console/multimedia"
PRIORITY = "optional"
RDEPENDS = "btfm"
PR = "r0"

inherit ccasefetch

COMPATIBLE_MACHINE = "omap-3430(s|l)dp"

CCASE_SPEC =   "%\
	element /vobs/WCGDev/... LINUX-WCG-BT_RLS_${PV}%\
	element /vobs/MCP_Common/... LINUX-WCG-BT_RLS_${PV}%\
	element /vobs/HSW_FMStack/... LINUX-WCG-BT_RLS_${PV}%\
	element * /main/LATEST %\
	"

CCASE_PATHFETCH = "/vobs/MCP_Common/ \
	/vobs/HSW_FMStack \
	/vobs/WCGDev \
	"
#CCASE_PATHFETCH = "/vobs/MCP_Common/Platform/fmhal/inc/int \
#	/vobs/MCP_Common/Platform/fmhal/inc \
#	/vobs/MCP_Common/Platform/os/linux \
#	/vobs/MCP_Common/Platform/inc \
#	/vobs/MCP_Common/tran \
#	/vobs/MCP_Common/inc \
#	/vobs/HSW_FMStack/stack/inc/int \
#	/vobs/HSW_FMStack/stack/inc \
#	/vobs/WCGDev/linux/fm_app \
#	/vobs/WCGDev/linux/build \
#	"
CCASE_PATHCOMPONENT = "vobs"
CCASE_PATHCOMPONENTS = "0"

do_chmod () {
	chmod -R +w ${S}
}

do_compile () {
	cd ${S}/WCGDev/linux/build/
	mkdir -p build_directory/fm_app

	cp makefile build_directory/fm_app
	cd build_directory/fm_app

	make BLUEZ_HEADERS=${STAGING_INCDIR} BLUEZ_LIB=${STAGING_LIBDIR} \
	    LIBPLACE=${STAGING_LIBDIR} \
	    CC=arm-none-linux-gnueabi-gcc \
	    AR=arm-none-linux-gnueabi-ar \
		INC_FLAGS="-I ${S}/MCP_Common/Platform/fmhal/inc/int \
		-I ${S}/MCP_Common/Platform/fmhal/inc \
		-I ${S}/MCP_Common/Platform/os/Linux \
		-I ${S}/MCP_Common/Platform/inc \
		-I ${S}/MCP_Common/tran \
		-I ${S}/MCP_Common/inc \
		-I ${S}/HSW_FMStack/stack/inc/int \
		-I ${S}/HSW_FMStack/stack/inc \
		-I ${STAGING_INCDIR} \
		-I ${S}/WCGDev/linux/fm_app \
		" \
		SRC_FOLDERS="${S}/MCP_Common/Platform/hw/linux \
		 ${S}/MCP_Common/Platform/fmhal/os \
		 ${S}/MCP_Common/Platform/os/linux \
		 ${S}/MCP_Common/frame \
		 ${S}/MCP_Common/ccm/vac \
		 ${S}/MCP_Common/ccm/ccm \
		 ${S}/MCP_Common/ccm/cal \
		 ${S}/MCP_Common/ccm/im \
		 ${S}/MCP_Common/ccm/im/test \
		 ${S}/MCP_Common/init_script \
		 ${S}/HSW_FMStack/stack/common \
		 ${S}/HSW_FMStack/stack/rx \
		 ${S}/HSW_FMStack/stack/tx \
		 ${S}/WCGDev/linux/fm_app \
		 "
}

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${S}/WCGDev/linux/build/build_directory/fm_app/fmapp \
	    ${D}${bindir}
}

addtask chmod after do_unpack_ccase before do_patch
