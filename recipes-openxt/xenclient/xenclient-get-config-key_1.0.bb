DESCRIPTION = "XenClient config partition key tool"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit xenclient

SRC_URI = "file://get-config-key.c \
"

S = "${WORKDIR}"

do_configure() {
    UUID=$(cat /proc/sys/kernel/random/uuid)
    sed -i "s/\"0\"/\"${UUID}\"/g" get-config-key.c
}

do_compile() {
	oe_runmake get-config-key
	${STRIP} get-config-key
}

do_install() {
	install -d ${D}${sbindir}
	install -m 0755 ${WORKDIR}/get-config-key ${D}${sbindir}
}
