require conf/license/license-gplv2.inc


SRC_URI = "file://${MACHINE}-shutdown.sh"

S = "${WORKDIR}"

INITSCRIPT_NAME = "odroid-shutdown"
INITSCRIPT_PARAMS = "start 39 0 ."

inherit pkgconfig update-rc.d

do_install() {
    install -d ${D}${INIT_D_DIR}/
    install -m 0755 ${WORKDIR}/${MACHINE}-shutdown.sh ${D}${INIT_D_DIR}/odroid-shutdown
}

COMPATIBLE_MACHINE = "^(odroidc2)$"
