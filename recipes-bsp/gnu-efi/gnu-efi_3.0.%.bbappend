# Force 64-bit version.
# Cheat and use the host environment. This would normaly use glibc headers to
# figure out basic informations. While using the headers might just work, using
# the 32bit libc of the target environment is just a recipe for failure.
# If this cannot be built in the target sysroot, there is not point in even
# using it, so build against the HOST, require the amd64 libc6 headers and
# pray.
# Forgive me, this is the sacrifice of what sanity I have left.
# If anything, this is yet another proof, if one is still required, that
# someone has to take the time to make dom0 64bits.
EXTRA_OEMAKE = " \
    'ARCH=x86_64' \
    'RANLIB=${RANLIB}' \
    'OBJCOPY=${OBJCOPY}' \
    'PREFIX=${prefix}' \
    'LIBDIR=${libdir}' \
"

INSANE_SKIP_${PN}-dev = "arch"
