package fr.unilasalle.fanech.tp_android

data class Rating (
    val rate: Float,
    val count: Int
)
{
    override fun toString(): String {
        return "Rating(rate=$rate, count=$count)"
    }
}