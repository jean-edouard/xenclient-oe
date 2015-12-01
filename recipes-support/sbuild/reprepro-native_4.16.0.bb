SRC_URI = "https://alioth.debian.org/frs/download.php/file/4109/reprepro_4.16.0.orig.tar.gz"
SRC_URI[md5sum] = "76dea7a4ece4fd1f5c2594ff99290c1d"
SRC_URI[sha256sum] = "fdd2cae3f23b26e3b44734925af5afb76486a46bde104254eb04d8344d98f591"

DEPENDS = " gpgme-native "

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=eb723b61539feef013de476e68b5c50a"

S = "${WORKDIR}/reprepro-${PV}"

inherit autotools native

BBCLASSEXTEND="native"
