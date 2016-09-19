require xen.inc

inherit pkgconfig pythonnative

DEPENDS += "util-linux xen-tools xen-blktap"

EXTRA_OEMAKE += "CROSS_SYS_ROOT=${STAGING_DIR_HOST} CROSS_COMPILE=${HOST_PREFIX}"
EXTRA_OEMAKE += "CONFIG_IOEMU=n"
EXTRA_OEMAKE += "DESTDIR=${D}"

TARGET_CC_ARCH += "${LDFLAGS}"

do_configure() {
        DESTDIR=${D} ./configure --prefix=${prefix}
}

do_compile() {
        oe_runmake -C tools/include xen-xsm/.dir
        oe_runmake LDLIBS_libxenctrl='-lxenctrl' \
		   LDLIBS_libxenstore='-lxenstore' \
		   LDLIBS_libblktapctl='-lblktapctl' \
		   LDLIBS_libxenguest='-lxenguest' \
		   -C tools subdir-all-libxl
}

do_install() {
        oe_runmake DESTDIR=${D} -C tools subdir-install-libxl
}
