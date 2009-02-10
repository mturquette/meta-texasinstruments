DESCRIPTION = "Task package for maemo environment"
LICENSE = "MIT"
ALLOW_EMPTY = "1"
PR = "r0"

#	task-fremantle-x11-plankton-libs-install \
#	task-fremantle-x11-plankton-libs-install-dbg \
#	task-fremantle-x11-plankton-libs-install-dev \
PACKAGES = "\
	task-fremantle-x11-plankton \
	task-fremantle-x11-plankton-dbg \
	task-fremantle-x11-plankton-dev \
	task-fremantle-x11-plankton-apps \
	task-fremantle-x11-plankton-apps-dbg \
	task-fremantle-x11-plankton-apps-dev \
	task-fremantle-x11-plankton-theme \
	task-fremantle-x11-plankton-theme-dbg \
	task-fremantle-x11-plankton-theme-dev \
	"
#	maemo-task-base \
#	maemo-task-apps \
#	maemo-task-libs-install \
#	maemo-task-theme"

#RDEPENDS_maemo-base-depends = "\
#	diet-x11 \
#	virtual/xserver \
#	xpext \
#	xsp"

#RDEPENDS_maemo-task-libs-install = "\
#missing
#	libsqlite \
#	libhildonwidgets \
#nonworking
#	hildon-lgpl \
#	libhildonbase \
#	hildon-fm"
#RDEPENDS_task-fremantle-x11-plankton-libs-install = "\

#RDEPENDS_maemo-task-base = "\
#	gdk-pixbuf-loader-png \
#	gdk-pixbuf-loader-xpm \
#	gdk-pixbuf-loader-jpeg \
#	pango-module-basic-x \
#	pango-module-basic-fc \
#	bluez-utils-dbus \
#	matchbox \
#	shared-mime-info \
#	rxvt-unicode \
#	xst \
#	xhost \
#	xrdb \
#	libgtkstylus \
#	outo \
#nonworking
#	osso-thumbnail \
RDEPENDS_task-fremantle-x11-plankton = "\
	libosso \
	osso-af-utils \
	osso-af-startup \
	osso-core-config \
	osso-gnome-vfs2 \
	hildon-initscripts \
	"
#	xauth \
#	esd"

#RDEPENDS_maemo-task-theme = "\
RDEPENDS_task-fremantle-x11-plankton-theme = "\
	xcursor-transparent-theme \
	sdk-default-theme \
	sdk-default-theme-config \
	sdk-default-icons \
	sapwood \
	ttf-bitstream-vera \
	osso-sounds"

#RDEPENDS_maemo-task-apps = "\
#nonworking
#	hildon-home \
#	hildon-navigator \
#	osso-screenshot-tool \
#	gpe-contacts-hildon \
#can't be built
#	hildon-status-bar \
#	osso-application-installer \
#	gpe-todo-hildon \
#	gpe-mini-browser-hildon"
RDEPENDS_task-fremantle-x11-plankton-apps = "\
	osso-gwobex \
	osso-gwconnect \
	osso-bttools \
	hildon-control-panel \
	osso-app-killer \
