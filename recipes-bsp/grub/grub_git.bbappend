PR .= ".1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-git:"

SRC_URI += " \
    file://remove-editing-and-shell.patch \
    file://accept-video-always.patch \
    "

PACKAGECONFIG = "device-mapper"
