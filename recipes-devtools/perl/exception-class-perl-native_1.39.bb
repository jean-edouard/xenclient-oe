SECTION = "libs"
LICENSE = "Artistic-1.0 | GPL-1.0+"

SRC_URI = "http://search.cpan.org/CPAN/authors/id/D/DR/DROLSKY/Exception-Class-${PV}.tar.gz"
SRC_URI[md5sum] = "971b6e47d18419bf1f8f0419ace2348c"
SRC_URI[sha256sum] = "819c756a69a6d1e31f396eca6a508b07b0a522a035b904cd1d432353cc362083"

S = "${WORKDIR}/Exception-Class-${PV}"

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
