require recipes-extended/xen/xen.inc

inherit autotools-brokensep update-rc.d

SRC_URI = "git://xenbits.xen.org/xen.git;protocol=git;branch=master \
           file://xenstored.initscript \
"
SRC_URI_append = ""

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbb4b1bdc2c3b6743da3c39d03249095"

DEPENDS = "xen"

PACKAGES = " \
    ${PN}-c \
    "

# OpenXT packages both the C and OCaml versions of XenStored.
# This recipe packages the C daemon
PROVIDES = "${PN} ${PN}-c"
RPROVIDES_${PN}-c = "${PN}"

COMPATIBLE_HOST = 'i686-oe-linux|(x86_64.*).*-linux|aarch64.*-linux'

FILES_${PN}-c = " \
    ${sbindir}/xenstored.xen-xenstored-c \
    ${localstatedir}/lib/xenstored \
    ${sysconfdir}/init.d/xenstored.xen-xenstored-c \
    ${sysconfdir}/xen/xenstored.conf \
    /usr/lib/libxenevtchn.so.1 /usr/lib/libxengnttab.so.1 /usr/lib/libxenctrl.so.4.10 \
    "

INITSCRIPT_PACKAGES = "${PN}-c"
INITSCRIPT_NAME_${PN}-c = "xenstored"
INITSCRIPT_PARAMS_${PN}-c = "defaults 05"

CFLAGS += "-I../../tools/xenstore/"

pkg_postinst_${PN}-c () {
    update-alternatives --install ${sbindir}/xenstored xenstored xenstored.${PN}-c 200
    update-alternatives --install ${sysconfdir}/init.d/xenstored xenstored-initscript xenstored.${PN}-c 200
}

pkg_prerm_${PN}-c () {
    update-alternatives --remove xenstored xenstored.${PN}-c
    update-alternatives --remove xenstored-initscript xenstored.${PN}-c
}

do_configure() {
    oe_runconf
    ln -s ../../xen/include/public tools/xenstore/xen
#    mkdir -p tools/xenstore/xen/io
#    mkdir -p tools/xenstore/xen/hvm
#    cp xen/include/public/hvm/dm_op.h tools/xenstore/xen/hvm/
#    cp xen/include/public/io/xs_wire.h tools/xenstore/xen/io/
#    cp xen/include/public/xen.h tools/xenstore/xen/
}

do_compile() {
    oe_runmake -C tools subdir-all-include
    oe_runmake -C tools subdir-all-libs
    oe_runmake -C tools subdir-all-libxc
#    oe_runmake -C tools subdir-all-flask
    oe_runmake -C tools subdir-all-xenstore
}

do_install() {
    mkdir -p ${D}/usr/share/pkgconfig
    mkdir -p ${D}/etc/init.d
#    oe_runmake DESTDIR=${D} -C tools subdir-install-include
    oe_runmake DESTDIR=${D} -C tools subdir-install-libs
    oe_runmake DESTDIR=${D} -C tools subdir-install-libxc
    oe_runmake DESTDIR=${D} -C tools subdir-install-xenstore

    rm ${D}/usr/lib/libxenguest.a ${D}/usr/lib/libxenguest.so ${D}/usr/lib/libxenctrl.a ${D}/usr/lib/libxenctrl.so
    rm ${D}/usr/include/xenctrl.h ${D}/usr/include/xenguest.h ${D}/usr/include/xentoollog.h

    mv ${D}${sbindir}/xenstored ${D}${sbindir}/xenstored.${PN}-c
    install -m 0755 ${WORKDIR}/xenstored.initscript \
                    ${D}${sysconfdir}/init.d/xenstored.${PN}-c

    # The C xenstored uses one additional command line argument:
    sed 's/EXECUTABLE --/EXECUTABLE --internal-db --/' \
        -i ${D}${sysconfdir}/init.d/xenstored.${PN}-c
}
