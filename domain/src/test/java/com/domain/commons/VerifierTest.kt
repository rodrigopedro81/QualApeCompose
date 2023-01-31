package com.domain.commons

import org.junit.Assert.*
import org.junit.Test
import kotlin.random.Random

class VerifierTest {

    private val verifier = Verifier()

    @Test
    fun `if returns true when apartment number is valid`() {
        assertEquals(verifier.isApartmentValid(Random.nextInt(100, 516).toString()), true)
    }

    @Test
    fun `if returns false when apartment number is invalid`() {
        assertEquals(verifier.isApartmentValid(Random.nextInt(10, 51).toString()), false)
    }

    @Test
    fun `if returns true when block number is valid`() {
        assertEquals(verifier.isBlockValid(Random.nextInt(1, 9).toString()), true)
    }

    @Test
    fun `if returns false when block number is invalid`() {
        assertEquals(verifier.isBlockValid(Random.nextInt(100, 900).toString()), false)
    }
}