SRC_URI = "git://git.debian.org/git/buildd-tools/schroot;protocol=git;branch=master"
SRCREV = "release/schroot-${PV}"

DEPENDS = "cppunit-naive boost-native "

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=5c520cebfab3c2fcc01082a9f91b8595"

S = "${WORKDIR}/git"

inherit autotools native

do_configure_prepend() {
    touch ChangeLog ABOUT-NLS
    echo ${PV} > VERSION
    mkdir -p scripts
    touch scripts/config.rpath
}

BBCLASSEXTEND="native"
