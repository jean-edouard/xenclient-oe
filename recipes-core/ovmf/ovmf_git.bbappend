SRC_URI += " \
    http://downloadmirror.intel.com/19186/eng/PREBOOT.EXE;unpack=0;name=PREBOOT \
"
DEPENDS_append += " \
    unzip-native \
"

# PREBOOT.EXE, OS independent, latest version (currently 24.2).
SRC_URI[PREBOOT.sha256sum] = "090137bc8af0b05c1ae2b27b8b7851d295cb1225880fb4657c7ddc470b261485"

do_extract_bootutil() {
    mkdir -p "${S}/Intel3.5/EFIX64"
    unzip -q -p "${WORKDIR}/PREBOOT.EXE" "APPS/EFI/EFIx64/E3522X2.EFI" > "${S}/Intel3.5/EFIX64/E3522X2.EFI"
}
addtask do_extract_bootutil before do_configure after do_unpack
do_extract_bootutil[doc] = "Extract Intel's proprietary E1000 NIC driver to be embedded in OVMF image."
do_extract_bootutil[depends] = "${PN}:do_prepare_recipe_sysroot"
do_extract_bootutil[dirs] = "${B}"

do_compile_class-target_append() {
    bbnote "Building with E1000 (support for netboot)."
    rm -rf ${S}/Build/Ovmf$OVMF_DIR_SUFFIX
    ${S}/OvmfPkg/build.sh $PARALLEL_JOBS -a $OVMF_ARCH -b RELEASE -t ${FIXED_GCCVER} -D E1000_ENABLE
    ln ${build_dir}/FV/OVMF.fd ${WORKDIR}/ovmf/ovmf.e1000.fd
    ln ${build_dir}/FV/OVMF_CODE.fd ${WORKDIR}/ovmf/ovmf.e1000.code.fd
}

do_install_class-target_append() {
    install -d ${D}${datadir}/firmware
    install -m 0600 ${WORKDIR}/ovmf/ovmf.e1000.fd ${D}${datadir}/firmware/ovmf.e1000.bin
    ln -sf ovmf.e1000.bin ${D}${datadir}/firmware/ovmf.bin
}

PACKAGES += " \
    ${PN}-firmware \
"

FILES_${PN}-firmware += " \
    ${datadir}/firmware \
"
