SUMMARY = "Recipe for packaging stubdomain kernel"
HOMEPAGE = "https://openxt.org"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM ?= "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

FILESEXTRAPATHS_prepend := "${DEPLOY_DIR}/images/${STUBDOMAIN_MACHINE}:"

SRC_URI = " \
        file://${STUBDOMAIN_KERNEL}-${STUBDOMAIN_MACHINE}.bin \
"

do_install() {
        install -d ${D}/usr/lib/xen/boot
        install -m 0644 ${WORKDIR}/${STUBDOMAIN_KERNEL}-${STUBDOMAIN_MACHINE}.bin \
            ${D}/usr/lib/xen/boot/stubdomain-bzImage
}

FILES_${PN} = "/usr/lib/xen/boot/stubdomain-bzImage"
