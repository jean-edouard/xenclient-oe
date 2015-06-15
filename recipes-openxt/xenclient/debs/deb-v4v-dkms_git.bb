inherit xenclient
inherit xenclient-deb
inherit xenclient-deb-dkms

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
DEPENDS = "${@deb_bootstrap_deps(d)}"

PV = "0+git${SRCPV}"

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/jean-edouard/pv-linux-drivers.git;protocol=${OPENXT_GIT_PROTOCOL};branch=${OPENXT_BRANCH}"

S="${WORKDIR}/git/xc-v4v"

DEB_SUITE = "wheezy"
DEB_ARCH = "i386"

DEB_EXTRA_PKGS = "lintian"

DEB_NAME = "openxt-v4v-dkms"
DEB_DESC = "OpenXT v4v dkms driver"

do_install() {
        mkdir -p "${D}/usr/share/${DEB_NAME}-${PR}"
	cp -a *.c include/ Makefile Kbuild dkms.conf "${D}/usr/share/${DEB_NAME}-${PR}"
	mkdir -p "${D}/oe-for-staging"
        install -D -m 0644 "${S}/include/xen/v4v.h" "${D}/oe-for-staging/usr/include/xen/v4v.h"
        install -D -m 0644 "${S}/include/linux/v4v_dev.h" "${D}/oe-for-staging/usr/include/linux/v4v_dev.h"
}
