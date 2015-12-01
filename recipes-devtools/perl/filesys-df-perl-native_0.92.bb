SECTION = "libs"
LICENSE = "Artistic-1.0 | GPL-1.0+"

SRC_URI = "http://www.cpan.org/modules/by-module/Filesys/Filesys-Df-${PV}.tar.gz"
SRC_URI[md5sum] = "a8b0aa3e5151a8a6c8b3067625980934"
SRC_URI[sha256sum] = "fe89cbb427e0e05f1cd97c2dd6d3866ac6b21bc7a85734ede159bdc35479552a"

S = "${WORKDIR}/Filesys-Df-${PV}"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

EXTRA_CPANFLAGS = "EXPATLIBPATH=${STAGING_LIBDIR_NATIVE} EXPATINCPATH=${STAGING_INCDIR_NATIVE}"

inherit cpan native

do_compile() {
	export LIBC="$(find ${STAGING_DIR_NATIVE}/${base_libdir}/ -name 'libc-*.so')"
	cpan_do_compile
}

FILES_${PN}-dbg += " /usr/lib/perl/vendor_perl/5.14.2/auto/Filesys/Df/.debug "

BBCLASSEXTEND="native"
