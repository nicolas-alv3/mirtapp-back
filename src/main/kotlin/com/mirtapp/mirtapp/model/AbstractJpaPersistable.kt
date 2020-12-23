package com.mirtapp.mirtapp.model

import org.springframework.data.util.ProxyUtils
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import java.io.Serializable
import javax.persistence.GenerationType

@MappedSuperclass
abstract class AbstractJpaPersistable<T : Serializable> {

    companion object {
        private val serialVersionUID = -5554308939380869754L
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: T? = null

    open fun getId(): T? {
        return id
    }

    open fun setId(newId : T?) {
        id =newId
    }

    override fun hashCode(): Int {
        return 31
    }

}