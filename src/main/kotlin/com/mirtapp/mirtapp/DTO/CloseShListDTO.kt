package com.mirtapp.mirtapp.DTO

data class CloseShListDTO(
        val userId : Long,
        val shListId : Long,
        val pendingIds : List<Long>
) {}
