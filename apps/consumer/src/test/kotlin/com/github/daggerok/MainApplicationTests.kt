package com.github.daggerok

import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestInstance(PER_CLASS)
@DisplayNameGeneration(ReplaceUnderscores::class)
class MainApplicationTests {

    @Test
    fun main_test() { }
}
