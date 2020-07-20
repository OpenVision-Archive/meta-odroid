BOX_BRAND = "hardkernel"

# Odroid BSP default settings
LINUX_VERSION_EXTENSION ?= ""

PREFERRED_PROVIDER_jpeg = "libjpeg-turbo"
PREFERRED_PROVIDER_jpeg-native = "libjpeg-turbo-native"

DVBMEDIASINK_CONFIG = "--with-wmv --with-pcm --with-eac3 --with-h265 --with-dtsdownmix"

EXTRA_OECONF_append_pn-enigma2 = " --with-alsa --with-alsacard=hw:0 --with-alsamixer=Master --with-fbdev=/dev/fb0 --with-lcddev=/dev/null --with-amlogic"
DEPENDS_append_pn-enigma2 = " libdvbcsa odroid-libamcodec-${MACHINE} odroid-libamadec-${MACHINE}"
EXTRA_OECONF_append_pn-tuxtxt-enigma2 = " --with-fbdev=/dev/fb0"
CC_append_pn-showiframe = " -DHAVE_AMLOGIC -I${STAGING_INCDIR}/amlogic/amcodec -lamcodec"
DEPENDS_append_pn-showiframe = " odroid-libamcodec-${MACHINE}"

GST_VERSION = "0.10"

SOC_FAMILY = "AMLS905"

require conf/distro/multilib.inc

IMAGE_CLASSES += "image_types_odroid"
require conf/machine/include/arm/arch-armv8.inc

DVBPROVIDER = "kernel"
DVBSKYPROVIDER = "kernel"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "\
	odroid-shutdown \
	hardkernel-mali-${MACHINE} \
	odroid-gst-amlavsink odroid-gst-amlavout gst-ffmpeg gst-fluendo-mpegdemux \
	kernel-modules \
	"

MACHINE_EXTRA_RRECOMMENDS += "\
	odroid-libamplayer-${MACHINE} \
	odroid-libamavutils-${MACHINE} \
	odroid-libamadec-${MACHINE} \
	odroid-libamcodec-${MACHINE} \
	"

KERNEL_MODULE_AUTOLOAD += "\
	sysfillrect \
	syscopyarea \
	mceusb \
	rc-core \
	mali \
	dhd \
	ch341 \
	rfcomm \
	hidp \
	cfg80211 \
	bluetooth \
	6lowpan-iphc \
	libcrc32c \
	xfs \
	"

module_conf_dhd = "options dhd firmware_path=/lib/firmware/brcm/ nvram_path=/lib/firmware/brcm/"

KERNEL_MODULE_PROBECONF += "\
	dhd \
	"

EXTRA_IMAGEDEPENDS += "u-boot-hardkernel"

SDCARD_ROOTFS ?= "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.ext4"
IMAGE_FSTYPES ?= "sdcard"

IMAGE_CMD_sdcard_append  = "\
    mkdir -p ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}; \
    mv ${SDIMG} ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}; \
    echo ${IMAGE_NAME} > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/imageversion; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.zip; \
    cd ${DEPLOY_DIR_IMAGE}; \
    zip ${IMAGE_NAME}_sdcard.zip ${IMAGEDIR}/*; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.ext4; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.img; \
    rm -Rf ${IMAGEDIR}; \
"

SERIAL_CONSOLE = "115200 ttyS0"

PREFERRED_PROVIDER_virtual/kernel ?= "linux-hardkernel"
PREFERRED_VERSION_linux-hardkernel ?= "3.14%"

#specify u-boot parameters
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-hardkernel"
PREFERRED_PROVIDER_u-boot ?= "u-boot-hardkernel"
PREFERRED_VERSION_u-boot-hardkernel ?= "2015.01"

KERNEL_IMAGETYPE ?= "Image"
UBOOT_BINARY ?= "u-boot.bin"
UBOOT_MACHINE_odroidc2 = "odroidc2_defconfig"

MACHINE_FEATURES = "serial usbgadget fb usbhost vfat ext2 alsa nolcd emmc nogamma nofhdskin multilib HDMI"

MTD_BLACK  = "mmcblk0"

# We know about rtc
MACHINE_FEATURES_BACKFILL_CONSIDERED += "rtc"
