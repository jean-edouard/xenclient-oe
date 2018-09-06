DESCRIPTION = "Console keymaps for XenClient"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILES_${PN} = "${datadir}/keymaps"

S = "${WORKDIR}/src"

do_compile() {
    :
}

do_install() {
    install -d ${D}${datadir}/keymaps
    touch ${D}${datadir}/keymaps/placeholder
}
