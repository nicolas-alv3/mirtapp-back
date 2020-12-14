package com.mirtapp.mirtapp.model

import javax.persistence.Entity

@Entity
class Product(
        var description:String,
        var price: Double = 0.0
): AbstractJpaPersistable<Long>() {
    constructor() : this("default",0.0) {
    }
    fun printOn(): String = """Descripcion: $description | Precio: $price"""
}

