package fr.unilasalle.fanech.tp_android

class Product
{
    private val id: Int;
    private val title: String;
    private val price: Float;
    private val image: String;

    constructor(id: Int, title: String, price: Float, image: String)
    {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
    }

    fun getId(): Int { return this.id; }
    fun getTitle(): String { return this.title; }
    fun getPrice(): Float { return this.price; }
    fun getImage(): String { return this.image; }


    override fun toString(): String
    {
        return "Product(id=$id, title=$title, price=$price, image=$image)";
    }

}