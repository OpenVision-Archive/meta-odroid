SUMMARY = "Hardkernel Enigma2 procfs drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"
require conf/license/license-close.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "^(odroidc2)$"

SRCDATE = "20161023"

PV = "${KV}+${SRCDATE}"

SRC_URI = "file://hardkernel-e2-procfs-${KV}-${SRCDATE}.zip"

S = "${WORKDIR}"

do_compile() {
}

do_install() {
	install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra
	install -m 0755 ${WORKDIR}/e2-procfs.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
}

do_package_qa() {
}

FILES_${PN} += "${nonarch_base_libdir}/modules/${KV}/extra"
