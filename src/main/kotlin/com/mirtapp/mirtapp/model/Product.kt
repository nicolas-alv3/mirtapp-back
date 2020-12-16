package com.mirtapp.mirtapp.model

import javax.persistence.Entity

@Entity
data class Product(
        val description:String = "",
        val price:Double = 0.0,
        val category : ProductCategory = ProductCategory.ALMACEN
) : AbstractJpaPersistable<Long>(){
}