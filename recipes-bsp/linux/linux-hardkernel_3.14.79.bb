inherit kernel machine_kernel_pr samba_change_dialect deploy

require recipes-kernel/linux/linux-yocto.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "^(odroidc2)$"

#KERNEL_DEVICETREE_odroidc2 = "meson64_odroidc2.dtb"

DEPENDS_append = " virtual/${TARGET_PREFIX}gcc"

SRC_URI = "https://github.com/OpenVisionE2/hardkernel/archive/odroidc2-enigma2.tar.gz \
	file://add_uboot.patch \
	file://defconfig \
	file://${OPENVISION_BASE}/meta-openvision/recipes-linux/kernel-patches/kernel-add-support-for-gcc${VISIONGCCVERSION}.patch \
	"

S = "${WORKDIR}/hardkernel-odroidc2-enigma2"

SRC_URI[md5sum] = "07d08a1d7839aac21fb376406c667208"
SRC_URI[sha256sum] = "91d75baeca446dfcc2a0edcceda377d0d9ffa33baa58a689754ac78abeacf3f8"

KCONF_BSP_AUDIT_LEVEL = "0"

do_compile_append() {
	oe_runmake dtbs 
}

do_deploy_append() {
	install -d ${DEPLOYDIR}
	install ${B}/arch/arm64/boot/dts/meson64_odroidc2.dtb ${DEPLOYDIR}/.
}

do_kernel_checkout() {
}
