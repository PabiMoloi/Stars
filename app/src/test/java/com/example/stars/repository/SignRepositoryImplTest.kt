package com.example.stars.repository

import com.example.stars.model.Sign
import com.example.stars.network.RetrofitService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import retrofit2.Response

@ExperimentalCoroutinesApi
class SignRepositoryImplTest {

    @Mock
    lateinit var retrofitService: RetrofitService
    private lateinit var signRepository: SignRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        signRepository = SignRepositoryImpl(retrofitService)
    }

    @Test
    fun getSignAndDay_200Response_ReturnsSignDetails() {
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
            //Given
            `when`(
                retrofitService.getSignAndDay(
                    anyString(),
                    anyString()
                )
            ).thenReturn(Response.success(signInfo))

            //when
            val result = signRepository.getSignAndDay(anyString(), anyString())

            //Then
            verify(retrofitService).getSignAndDay(anyString(), anyString())
            assertEquals(result.body(), signInfo)
        }
    }
}