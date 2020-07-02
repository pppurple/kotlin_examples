package com.example.kotlin.sandbox.contract

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

class MainTest{
    @Test
    fun personTest() {
        // if not use contract
        val person = getPerson()
        assertThat(person).isNotNull
        assertThat(person!!.age).isEqualTo(40)
        assertThat(person.name).isEqualTo("Dad")
    }

    @Test
    @ExperimentalContracts
    fun personTestUsingContract() {
        val person = getPerson()
        assertThatNotNull(person)
        assertThat(person.age).isEqualTo(40)
        assertThat(person.name).isEqualTo("Dad")
    }

    private fun getPerson(): Person? {
        // emulate searching from DB
        return Person(
            age = 40,
            name = "Dad"
        )
    }

    data class Person(
        val age:Int,
        val name:String
    )
}

@ExperimentalContracts
fun assertThatNotNull(actual: Any?) {
    contract {
        returns() implies (actual != null)
    }
}
