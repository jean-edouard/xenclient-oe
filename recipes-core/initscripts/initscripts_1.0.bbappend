PR .= ".1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI_OVERRIDES_PACKAGE_ARCH = "1"

SRC_URI += "file://read-only-rootfs-hook.sh \
	"
