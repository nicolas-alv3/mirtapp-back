package com.mirtapp.mirtapp.DTO

import com.mirtapp.mirtapp.model.ProductMeasure

class ItemDTO(
        val amount: Int,
        val measure: ProductMeasure,
        val productId: Long
) {

}
