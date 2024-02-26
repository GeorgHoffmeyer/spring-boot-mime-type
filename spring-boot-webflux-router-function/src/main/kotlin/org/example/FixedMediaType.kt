package org.example

import org.springframework.http.MediaType

class FixedMediaType(private val type: String, private val subtype: String, private val parameters: Map<String, String>): MediaType(type, subtype, parameters){

    constructor(mediaType: MediaType) : this(mediaType.type, mediaType.subtype, mediaType.parameters)
    override fun isCompatibleWith(other: MediaType?): Boolean {
        var isCompatibleWithByMimeType = super.isCompatibleWith(other)

        if(other==null){
            return false
        }
        if(this.parameters.isEmpty() || other.parameters.isEmpty()){
            return isCompatibleWithByMimeType
        }

        this.parameters.forEach {
            if(other?.parameters?.contains(it.key) == false || other.parameters.get(it.key) != it.value){
                return false
            }
        }

        return true
    }
}