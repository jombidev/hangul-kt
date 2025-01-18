package dev.jombi.hangulkt

internal inline fun <S, T> Iterable<T>.reduce(initial: S, operation: (acc: S, T) -> S): S {
    val iterator = this.iterator()
    var accumulator: S = initial
    while (iterator.hasNext()) {
        accumulator = operation(accumulator, iterator.next())
    }
    return accumulator
}
