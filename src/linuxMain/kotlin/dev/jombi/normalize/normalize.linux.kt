package dev.jombi.normalize

import kotlinx.cinterop.*
import platform.posix.size_tVar
import uninorm.UNINORM_NFC
import uninorm.UNINORM_NFD
import uninorm.UNINORM_NFKC
import uninorm.UNINORM_NFKD
import uninorm.u8_normalize

/**
 * Normalize the string according to the specified normalization form.
 *
 * Note: This method does not change the original string.
 */
@OptIn(ExperimentalForeignApi::class)
public actual fun String.normalize(form: Form): String = memScoped {
    val str = this@normalize.utf8
    val uninormForm = when (form) {
        Form.NFC -> UNINORM_NFC
        Form.NFD -> UNINORM_NFD
        Form.NFKC -> UNINORM_NFKC
        Form.NFKD -> UNINORM_NFKD
    }
    val result = u8_normalize(
        uninormForm, str.ptr.reinterpret(), str.size.convert(), null, alloc<size_tVar>().ptr
    )!!
    result.reinterpret<ByteVar>().toKString()
}