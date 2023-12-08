package fr.unilasalle.fanech.tp_android

class Product
{
    private val id: Int;
    private val title: String;
    private val price: Float;

    constructor(id: Int, title: String, price: Float)
    {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    fun getId(): Int { return this.id; }
    fun getTitle(): String { return this.title; }
    fun getPrice(): Float { return this.price; }


    override fun toString(): String
    {
        return "Product(id=$id, title=$title, price=$price)";
    }

}