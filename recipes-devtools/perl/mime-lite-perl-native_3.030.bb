SECTION = "libs"
LICENSE = "Artistic-1.0 | GPL-1.0+"

SRC_URI = "http://search.cpan.org/CPAN/authors/id/R/RJ/RJBS/MIME-Lite-${PV}.tar.gz"
SRC_URI[md5sum] = "5a6d90329e049eee77248d667343acc7"
SRC_URI[sha256sum] = "8f39901bc580bc3dce69e10415305e4435ff90264c63d29f707b4566460be962"

S = "${WORKDIR}/MIME-Lite-${PV}"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

#EXTRA_CPANFLAGS = "LIBS='-L=${STAGING_LIBDIR_NATIVE} -L=${STAGING_BASELIBDIR_NATIVE}' \
#                   INC=-I=${STAGING_INCDIR_NATIVE}                                    \
#                   EXPATLIBPATH=${STAGING_LIBDIR_NATIVE} EXPATINCPATH=${STAGING_INCDIR_NATIVE}"

inherit cpan native

#do_compile() {
#	export LIBC="$(find ${STAGING_DIR_NATIVE} -name 'libc-*.so' | head -1)"
#        echo JEDDD
#        echo $LIBC
#        echo "LIBS='-L=${STAGING_LIBDIR_NATIVE} -L=${STAGING_BASELIBDIR_NATIVE}'"
#        echo "INC=-I=${STAGING_INCDIR_NATIVE}"
#        echo "EXPATLIBPATH=${STAGING_LIBDIR_NATIVE} EXPATINCPATH=${STAGING_INCDIR_NATIVE}"
#	cpan_do_compile
#}

BBCLASSEXTEND="native"
