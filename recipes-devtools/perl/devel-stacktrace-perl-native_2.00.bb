SECTION = "libs"
LICENSE = "Artistic-1.0 | GPL-1.0+"

SRC_URI = "http://search.cpan.org/CPAN/authors/id/D/DR/DROLSKY/Devel-StackTrace-${PV}.tar.gz"
SRC_URI[md5sum] = "826ed2bc7cdd8d852d7d2d8b69aa313c"
SRC_URI[sha256sum] = "1debe7273099a60e1386e0da5edbed7334db3cf3ed8e3b4106b087100b8ec5e4"

S = "${WORKDIR}/Devel-StackTrace-${PV}"

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
