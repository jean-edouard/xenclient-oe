PR .= ".1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += "file://etc-config-passwd.patch \
            file://enable-core-dumps.patch \
"

EXTRA_OECONF += "--disable-nis"

