DESCRIPTION = "GPS Navilink+MCPF Framework for OMAP"
LICENSE = "GPL+LGPL"
SECTION = "base"
PR = "r1"
 
 
 
FILES_${PN}-dbg += "${bindir}/gps/.debug"
FILES_${PN}-dbg += "/usr/lib/gps-navilink/.debug"
FILES_${PN}-dbg += "${sysconfdir}/init.d/.debug"
FILES_${PN}-dbg += "${sysconfdir}/rc5.d/.debug"
 
inherit autotools pkgconfig
inherit ccasefetch
COMPATIBLE_MACHINE = "omap-3430(l|s)dp"
 
CCASE_SPEC = "%\
        element /vobs/WCGDev/... LINUX-WCG-GPS_REL_${PV}%\
        element * /main/LATEST %\
        "
 
CCASE_PATHFETCH = "/vobs/WCGDev/linux/gps/MCP_Common/frame \
        /vobs/WCGDev/linux/gps/MCP_Common/tran \
        /vobs/WCGDev/linux/gps/MCP_Common/Platform \
        /vobs/WCGDev/linux/gps/MCP_Common/inc \
        /vobs/WCGDev/linux/gps/NaviLink/ \
        /vobs/WCGDev/linux/gps/gpio_driver/ \
        /vobs/WCGDev/linux/gps/general/ \
        "
 
CCASE_PATHCOMPONENT = "vobs"
CCASE_PATHCOMPONENTS = "0"
 
PACKAGES = "${PN}-dbg ${PN} ${PN}-gps"
 
# Added temporarily disable stripping of gps_gpio_module.ko
STRIP=""
 
do_compile() {
# Building MCPF+NAVC+testApplication
  cd ${S}/WCGDev/linux/gps/MCP_Common/Platform/os/linux/build
  make CC=arm-none-linux-gnueabi-gcc
}
 
 
do_install() {
# Installing the testApp executable and GPSC config files
  install -d ${D}${bindir}/gps
  install -m 0755 ${S}/WCGDev/linux/gps/MCP_Common/Platform/os/linux/build/arm/gps/GPSC* ${D}${bindir}/gps
  install -m 0755 ${S}/WCGDev/linux/gps/MCP_Common/Platform/os/linux/build/arm/gps/patch-X.0.ce ${D}${bindir}/gps
  install -m 0755 ${S}/WCGDev/linux/gps/MCP_Common/Platform/os/linux/build/arm/gps/gpsTestApp ${D}${bindir}/gps
 
 
# Installing the Gpio init scripts to module insertion at bootup
        install -d ${D}/usr/lib/gps-navilink
        install -m 0755 ${S}/WCGDev/linux/gps/gpio_driver/gps_gpio_module.ko ${D}/usr/lib/gps-navilink
        install -m 0755 ${S}/WCGDev/linux/gps/general/vspm.ko ${D}/usr/lib/gps-navilink
 
# Installing the bootup configuration for gpio drivers
        install -d ${D}${sysconfdir}/init.d
        install -d ${D}${sysconfdir}/rc5.d
 
  install -m 0755 ${S}/WCGDev/linux/gps/gpio_driver/gps ${D}${sysconfdir}/init.d
  chmod 777 ${D}${sysconfdir}/init.d/gps
        ln -sf ../init.d/gps ${D}${sysconfdir}/rc5.d/S23gps
}
 
FILES_${PN}-gps += "\
        ${bindir}/* \
        ${sysconfdir}/* "
 
