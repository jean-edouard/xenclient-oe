SECTION = "libs"
LICENSE = "Artistic-1.0 | GPL-1.0+"

SRC_URI = "http://search.cpan.org/CPAN/authors/id/G/GB/GBARR/TimeDate-${PV}.tar.gz"
SRC_URI[md5sum] = "b1d91153ac971347aee84292ed886c1c"
SRC_URI[sha256sum] = "75bd254871cb5853a6aa0403ac0be270cdd75c9d1b6639f18ecba63c15298e86"

S = "${WORKDIR}/TimeDate-${PV}"

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
