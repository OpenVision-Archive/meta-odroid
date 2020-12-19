SUMMARY = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"
require conf/license/license-close.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "^(odroidc2)$"

SRCDATE = "20161126"

PV = "${KV}+${SRCDATE}"

RDEPENDS_${PN} += "hardkernel-e2-procfs-${MACHINE} firmware-avl6211 firmware-mn88436 firmware-ap6210 firmware-dvb-usb-af9015"

SRC_URI = "file://hardkernel-dvb-modules-${KV}-${SRCDATE}.zip"

S = "${WORKDIR}"

do_compile() {
}

do_install() {
	install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra
	install -m 0755 ${WORKDIR}/hardkerneldvb.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
	install -d ${D}${sysconfdir}/modules-load.d
	echo "hardkerneldvb" > ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
}

FILES_${PN} += "${sysconfdir}/modules-load.d/_${MACHINE}.conf ${nonarch_base_libdir}/modules/${KV}/extra"

do_package_qa() {
}
