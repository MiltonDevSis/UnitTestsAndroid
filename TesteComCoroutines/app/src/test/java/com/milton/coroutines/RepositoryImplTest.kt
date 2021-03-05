package com.milton.coroutines

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RepositoryImplTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val apiService: APIService = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun`when fetchCatFact  is called then it should call service fectchCatFct`(){

        coEvery { apiService.fetchCatFact() } returns CatFact("1234")

        runBlocking {
            RepositoryImpl(apiService).fetchCatFact()
        }

        coVerify { apiService.fetchCatFact() }
    }
}