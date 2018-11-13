FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append += "\
	${@bb.utils.contains("MACHINE_FEATURES", "odroid-pli", "file://revert-gamma-changes.patch", "", d)} \
	"
