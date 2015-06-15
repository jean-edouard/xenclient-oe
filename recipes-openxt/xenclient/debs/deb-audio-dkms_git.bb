inherit xenclient
inherit xenclient-deb
inherit xenclient-deb-dkms

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
DEPENDS = "${@deb_bootstrap_deps(d)}"

PV = "0+git${SRCPV}"

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/jean-edouard/pv-linux-drivers.git;protocol=${OPENXT_GIT_PROTOCOL};branch=${OPENXT_BRANCH}"

S="${WORKDIR}/git/xc-audio"

DEB_SUITE = "wheezy"
DEB_ARCH = "i386"

DEB_EXTRA_PKGS = "lintian"

DEB_NAME = "openxt-audio-dkms"
DEB_DESC = "OpenXT audio dkms driver"

do_install() {
        mkdir -p "${D}/usr/share/${DEB_NAME}-${PR}"
	cp -a *.c *.h Makefile Kbuild dkms.conf "${D}/usr/share/${DEB_NAME}-${PR}"
}
