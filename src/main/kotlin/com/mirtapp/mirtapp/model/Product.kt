package com.mirtapp.mirtapp.model

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name="product")
data class Product(
        val description:String = "",
        val price:Double = 0.0,
        val category : ProductCategory = ProductCategory.ALMACEN,
        @OneToMany(cascade = [CascadeType.ALL])
        val providers: MutableList<Provider> = mutableListOf()
) : AbstractJpaPersistable<Long>(){
}