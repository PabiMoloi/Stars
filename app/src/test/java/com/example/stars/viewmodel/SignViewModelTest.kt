package com.example.stars.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.stars.model.Sign
import com.example.stars.repository.SignRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import retrofit2.Response


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class SignViewModelTest {

    @Mock
    lateinit var repository: SignRepository
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var viewModel: SignViewModel
    private val mainThreadSurrogate = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = SignViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun retrieveSignInfo() {
        val signInfo = Sign(
            "16 Nov",
            "Aries",
            "5pm",
            "7",
            "Blue",
            "16 Nov - 22 Dec",
            "Sad",
            "Today is your lucky day."
        )
        runBlocking {
            launch(Dispatchers.Main) {
                `when`(repository.getSignAndDay(anyString(), anyString())).thenReturn(
                    Response.success(
                        signInfo
                    )
                )
                viewModel.retrieveSignInfo(anyString(), anyString())
                verify(repository).getSignAndDay(anyString(), anyString())
            }
        }
    }

}