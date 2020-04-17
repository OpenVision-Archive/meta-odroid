inherit kernel machine_kernel_pr
require recipes-kernel/linux/linux-yocto.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

PV = "3.14.79"

#KERNEL_DEVICETREE_odroidc2 = "meson64_odroidc2.dtb"

SRC_URI = "https://github.com/OpenVisionE2/hardkernel/archive/odroidc2-enigma2.tar.gz \
    file://add_uboot.patch \
    file://defconfig \
    file://${OPENVISION_BASE}/openvision-oe/recipes-linux/kernel-patches/kernel-add-support-for-gcc6.patch \
"

S = "${WORKDIR}/hardkernel-odroidc2-enigma2"

SRC_URI[md5sum] = "07d08a1d7839aac21fb376406c667208"
SRC_URI[sha256sum] = "91d75baeca446dfcc2a0edcceda377d0d9ffa33baa58a689754ac78abeacf3f8"

KCONF_BSP_AUDIT_LEVEL = "0"

do_compile_append() {
	oe_runmake dtbs 
}

inherit deploy

do_deploy_append() {
	install -d ${DEPLOYDIR}
	install ${B}/arch/arm64/boot/dts/meson64_odroidc2.dtb ${DEPLOYDIR}/.
}

COMPATIBLE_MACHINE = "^(odroidc2)$"
