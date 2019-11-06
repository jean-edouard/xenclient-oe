# webkitgtk tends to use *a lot* of memory at build time. With 8G RAM & 8G swap
# the OOM killer is quite keen on reaping the build process:
# Out of memory: Killed process 3088501 (cc1plus)
# While this is arbitrary, it seem to work with 8G of RAM, -j 8 was already too
# agressive.
PARALLEL_MAKE = "-j 4"
