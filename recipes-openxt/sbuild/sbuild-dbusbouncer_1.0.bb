DESCRIPTION = "XenClient DBUS socket connections dom0-uivm"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
DEPENDS = "libv4v"
RDEPENDS += "xen-tools-libxenstore"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "file://dbusbouncer.c \
	   file://dbusbouncer.initscript \
"

INITSCRIPT_NAME = "dbusbouncer"
INITSCRIPT_PARAMS = "defaults 29"

S = "${WORKDIR}"

inherit update-rc.d xenclient xenclient-sbuild

SBUILD_DESC = "V4V - UNIX socket proxy"
SBUILD_SUITE = "wheezy"
SBUILD_ARCH = "i386"
SBUILD_DEPENDS = ""
SBUILD_RDEPENDS = ""
SBUILD_SECTION = "misc"

LDFLAGS += "-lv4v -lxenstore"

ASNEEDED = ""

do_compile() {
	oe_runmake dbusbouncer
}

do_install() {
	install -d ${D}${sbindir}
	install -m 0755 ${WORKDIR}/dbusbouncer ${D}${sbindir}
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/dbusbouncer.initscript ${D}${sysconfdir}/init.d/dbusbouncer

	${STRIP} ${D}${sbindir}/dbusbouncer
}

DEBUG_BUILD = "1"
