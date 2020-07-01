
PV = "11.0.7"
PV_UPDATE = "10"
VERSION = "11.39.15-ca"
BUILD_NUMBER = "11.0.7"
SUFFIX = "linux_x64"

SUMMARY = "Azul Zulu Java Development Kit (JDK) binaries"
DESCRIPTION = "This the Embedded JDK for the 64 bit Intel architecture from \
 Azul Systems Inc. It is created using OpenJDK code, which is licensed under \
 GPLv2 and various other third party licenses. Azul offers a variety of \
 support plans, as well as patent indemnification and guarantees against \
 the risk of open source viral contamination, as part of its Zulu \
 Embedded commercial offerings."

BBCLASSEXTEND = "native"

LICENSE = "GPL-2.0-with-classpath-exception"
LIC_FILES_CHKSUM = "file://zulu${VERSION}-jdk${BUILD_NUMBER}-${SUFFIX}/legal/jdk.attach/LICENSE;md5=3e0b59f8fac05c3c03d4a26bbda13f8f"

SRC_URI="https://cdn.azul.com/zulu/bin/zulu${VERSION}-jdk${BUILD_NUMBER}-${SUFFIX}.tar.gz"

SRC_URI[md5sum] = "081b4b210e8ac8e70ccbbbdc6c474fd7"
SRC_URI[sha256sum] = "df0de67998ac0c58b3c9e83c86e2a81daca05dc5adc189d942bc5d3f4691e749"

PR = "u${PV_UPDATE}"
S = "${WORKDIR}"

do_install () {
  install -d -m 0755 ${D}${datadir}/zulu-${PV}_${PV_UPDATE}
  cp -a ${S}/zulu${VERSION}-jdk${BUILD_NUMBER}-${SUFFIX}/* ${D}${datadir}/zulu-${PV}_${PV_UPDATE}
  install -d -m 0755 ${D}${bindir}
  ln -sf ${datadir}/zulu-${PV}_${PV_UPDATE}/bin/java ${D}${bindir}/java

  install -d ${D}/lib64
  ln -s -r ${D}/lib/ld-linux-x86-64.so.2 ${D}/lib64/ld-linux-x86-64.so.2
}

# All the files are provided in a binaray package, and keeping all the
# files in a single package causes packaging QA errors and warnings.
# Avoid these packaging failure by skiping all the QA checks
INSANE_SKIP_${PN} = "${ERROR_QA} ${WARN_QA}"

FILES_${PN} = "/usr/ \
               /lib64/ld-linux-x86-64.so.2 \
                "
RPROVIDES_${PN} = "zulu-jdk"
PROVIDES += "virtual/java"

DEPENDS = "alsa-lib libxi libxrender libxtst"

