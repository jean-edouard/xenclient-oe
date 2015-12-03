SRC_URI = "git://anonscm.debian.org/dpkg/dpkg.git;protocol=git;branch=1.17.x     \
           file://configure.patch"
SRCREV = "${PV}"

DEPENDS = "perl-native"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

S = "${WORKDIR}/git"

EXTRA_OECONF += " --disable-dselect "

do_configure_prepend() {
      mkdir -p build-aux
      touch build-aux/config.rpath
}

do_install_append() {
      mkdir -p ${D}/${STAGING_DIR_NATIVE}/usr/lib/perl-native/perl
      ln -s ${D}/${STAGING_DIR_NATIVE}/usr/share/perl5/Dpkg ${D}/${STAGING_DIR_NATIVE}/usr/lib/perl-native/perl/Dpkg
      ln -s ${D}/${STAGING_DIR_NATIVE}/usr/share/perl5/Dpkg.pm ${D}/${STAGING_DIR_NATIVE}/usr/lib/perl-native/perl/Dpkg.pm
}

inherit gettext autotools native
