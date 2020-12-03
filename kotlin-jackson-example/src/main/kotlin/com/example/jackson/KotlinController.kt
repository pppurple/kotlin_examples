package com.example.jackson

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class KotlinController {
    @GetMapping("/kotlin/class")
    fun getKotlinPerson(): KotlinPerson {
        return KotlinPerson(
            name = "test",
            canWalk = true,
            isTestUser = true
        )
    }

    @GetMapping("/kotlin/dataclass")
    fun getKotlinPersonDataClass(): KotlinPersonDataClass {
        return KotlinPersonDataClass(
            name = "test",
            canWalk = true,
            isTestUser = true
        )
    }

    @GetMapping("/kotlin/dataclassWithJsonProperty")
    fun getKotlinPersonWithJsonProperty(): KotlinPersonWithJsonProperty {
        return KotlinPersonWithJsonProperty(
            name = "test",
            canWalk = true,
            isTestUser = true
        )
    }

    class KotlinPerson(
        val name: String,
        val canWalk: Boolean,
        val isTestUser: Boolean
    )

    data class KotlinPersonDataClass(
        val name: String,
        val canWalk: Boolean,
        val isTestUser: Boolean
    )

    class KotlinPersonWithJsonProperty(
        val name: String,
        val canWalk: Boolean,
        @get:JsonProperty("testUser")
        val isTestUser: Boolean
    )
}
