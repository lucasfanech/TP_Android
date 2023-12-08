package fr.unilasalle.fanech.tp_android

data class Product(
    val id: Int,
    val title: String,
    val price: Float,
    val image: String,
    val description: String,
    val category: String,
    val rating: Rating
)
{
    override fun toString(): String {
        return "Product(id=$id, title='$title', price=$price, image='$image', description='$description', category='$category', rating=$rating)"
    }
}
