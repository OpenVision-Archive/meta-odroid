require conf/license/license-gplv2.inc

PV = "1.0"

SRC_URI = "file://${MACHINE}-shutdown.sh"

S = "${WORKDIR}"

INITSCRIPT_NAME = "odroid-shutdown"
INITSCRIPT_PARAMS = "start 39 0 ."

inherit pkgconfig update-rc.d

do_install() {
    install -d ${D}${sysconfdir}/init.d/
    install -m 0755 ${WORKDIR}/${MACHINE}-shutdown.sh ${D}${sysconfdir}/init.d/odroid-shutdown
}

COMPATIBLE_MACHINE = "^(odroidc2)$"
