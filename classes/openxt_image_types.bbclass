# raw image - simply copy rootfs tree to deploy directory
IMAGE_CMD_raw() {
    cp -a ${IMAGE_ROOTFS} ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.raw
}

# OpenXT ext3 tweaks.
# - Disable fscheck.
# - Run fs check after generation.
IMAGE_CMD_ext3_append() {
    ;
    tune2fs -c -1 -i 0 ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.ext3
    e2fsck -f -y ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.ext3
}

# OpenXT vhd.
# Standard vhd image from an existing filesystem.
CONVERSIONTYPES_append = " vhd"

CONVERSION_CMD_vhd() {
    # ROOTFS_SIZE units are Kilobytes
    local TARGET_SIZE_KB="${ROOTFS_SIZE}"
    local READ_SIZE_KB=$( du -Lbsk ${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${type} | cut -f 1 )
    # A .disk.vhd will have a size larger than ROOTFS_SIZE
    if [ "$READ_SIZE_KB" -gt "$TARGET_SIZE_KB" ] ; then
        bbnote "Overriding TARGET_SIZE_KB=$TARGET_SIZE_KB with READ_SIZE_KB=$READ_SIZE_KB"
        TARGET_SIZE_KB="$READ_SIZE_KB"
    fi

    # vhd size must be a multiple of 2 MB.
    local ALIGN_KB=`expr 2 \* 1024`
    local TARGET_VHD_SIZE_KB=`expr \( \( $TARGET_SIZE_KB + ${ALIGN_KB} - 1 \) / ${ALIGN_KB} \) \* ${ALIGN_KB}`
    local TARGET_VHD_SIZE_MB=`expr \( ${TARGET_VHD_SIZE_KB} / 1024 \)`
    vhd convert ${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${type} ${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${type}.vhd ${TARGET_VHD_SIZE_MB}
}

CONVERSION_DEPENDS_vhd = "hkg-vhd-native"
