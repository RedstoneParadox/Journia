package io.github.redstoneparadox.journia.util

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import java.util.*

object Codecs {
    val INT_RANGE: Codec<IntRange> = createRangeCodec(Codec.INT, ::IntRange)
    val NULLABLE_INT: Codec<Int?> = nullableCodec(Codec.INT)

    private inline fun <reified T, reified U: ClosedRange<T>> createRangeCodec(valueCodec: Codec<T>, noinline factory: (T, T) -> U): Codec<U> {
        return RecordCodecBuilder.create { instance ->
            instance.group(
                valueCodec.fieldOf("start").forGetter { it.start },
                valueCodec.fieldOf("endInclusive").forGetter { it.endInclusive }
            ).apply(instance, factory)
        }
    }

    private inline fun <reified T> nullableCodec(valueCodec: Codec<T>): Codec<T?> {
        return valueCodec.optionalFieldOf("value").xmap(
            { if (it.isPresent) it.get() else null; },
            { if (it == null) Optional.empty() else Optional.of(it) }
        ).codec()
    }
}