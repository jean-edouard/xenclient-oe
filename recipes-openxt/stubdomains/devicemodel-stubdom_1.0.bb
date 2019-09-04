SUMMARY = "Recipe for packaging qemu stubdomain image"
HOMEPAGE = "https://openxt.org"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM ?= "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

FILESEXTRAPATHS_prepend := "${DEPLOY_DIR}/images/${STUBDOMAIN_MACHINE}:"

SRC_URI = " \
        file://xenclient-stubdomain-initramfs-image-${STUBDOMAIN_MACHINE}.cpio.gz;unpack=0 \
"

do_install() {
        install -d ${D}/usr/lib/xen/boot
        install -m 0644 ${WORKDIR}/xenclient-stubdomain-initramfs-image-${STUBDOMAIN_MACHINE}.cpio.gz \
            ${D}/usr/lib/xen/boot/stubdomain-initramfs
}

FILES_${PN} = "/usr/lib/xen/boot/stubdomain-initramfs"

RDEPENDS_${PN} = "${STUBDOMAIN_MACHINE}-kernel"
