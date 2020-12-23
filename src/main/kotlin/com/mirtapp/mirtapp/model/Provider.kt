package com.mirtapp.mirtapp.model

import javax.persistence.Entity

@Entity
data class Provider(val name : String = "") : AbstractJpaPersistable<Long>(){
}
