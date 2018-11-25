package com.pdg.androidbasics.model

class Item(
    id: Int,
    name: String,
    subtitle: String,
    image: String,
    price: Float,
    description: String
) {
    var id: Int = 0
        internal set
    var name: String
        internal set
    var subtitle: String
        internal set
    var image: String
        internal set
    var price: Float = 0.toFloat()
        internal set
    var description: String
        internal set

    init {
        this.id = id
        this.name = name
        this.subtitle = subtitle
        this.image = image
        this.price = price
        this.description = description

    }
}
