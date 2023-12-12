package fr.unilasalle.fanech.tp_android

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Rating (
    val rate: Float,
    val count: Int
) : Parcelable {
    override fun toString(): String {
        return "Rating(rate=$rate, count=$count)"
    }
}