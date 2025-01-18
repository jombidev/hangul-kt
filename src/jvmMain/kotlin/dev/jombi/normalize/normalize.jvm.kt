package dev.jombi.normalize

import java.text.Normalizer

/**
 * Normalize the string according to the specified normalization form.
 *
 * Note: This method does not change the original string.
 */
public actual fun String.normalize(form: Form): String {
    return Normalizer.normalize(this, when (form) {
        Form.NFC -> Normalizer.Form.NFC
        Form.NFD -> Normalizer.Form.NFD
        Form.NFKC -> Normalizer.Form.NFKC
        Form.NFKD -> Normalizer.Form.NFKD
    })
}