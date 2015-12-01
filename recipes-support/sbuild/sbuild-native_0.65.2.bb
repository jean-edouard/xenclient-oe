SRC_URI = "git://anonscm.debian.org/buildd-tools/sbuild;protocol=git;branch=master"
SRCREV = "release/sbuild-${PV}"

DEPENDS = "perl-native filesys-df-perl-native"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=4325afd396febcb659c36b49533135d4"

S = "${WORKDIR}/git"

inherit autotools native

do_configure_prepend() {
# NOTE: configure calls a test program, that uses the hardcoded /usr/bin/perl
# Therefore, the host has to have perl installed...
# If it's ever considered a problem, some sed-ing in at least test/perl-syntax.in should do the trick
    cp ChangeLog-buildd ChangeLog
    echo ${PV} > VERSION

# The builder for sbuild.conf is buggy, don't build it and provide our own conf file
    sed -i 's/SUBDIRS =\(.*\) etc \(.*\)$/SUBDIRS =\1 \2/' Makefile.am
# Man pages have trouble too, don't need them:
    sed -i 's/SUBDIRS =\(.*\) man \(.*\)$/SUBDIRS =\1 \2/' Makefile.am
}

do_compile_prepend() {
    export PATH="${STAGING_DIR_NATIVE}/usr/bin/perl-native:$PATH"
    sed -i 's|^#\!/usr/bin/perl$|#!/usr/bin/env perl|' tools/sbuild-dumpconfig
}

#FILES_${PN} += " /usr/share "

BBCLASSEXTEND="native"
