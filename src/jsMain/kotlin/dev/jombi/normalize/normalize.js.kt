package dev.jombi.normalize

/**
 * Normalize the string according to the specified normalization form.
 *
 * Note: This method does not change the original string.
 */
public actual fun String.normalize(form: Form): String {
    return asDynamic().normalize(form.name) as String
}