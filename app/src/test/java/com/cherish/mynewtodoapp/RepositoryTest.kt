package com.cherish.mynewtodoapp

import app.cash.turbine.test
import com.cherish.mynewtodoapp.data.local.db.NewDao
import com.cherish.mynewtodoapp.data.model.api.GetNewsResponse
import com.cherish.mynewtodoapp.data.model.api.Source
import com.cherish.mynewtodoapp.data.model.db.NewsEntity
import com.cherish.mynewtodoapp.data.remote.ApiService
import com.cherish.mynewtodoapp.data.remote.ResultManager
import com.cherish.mynewtodoapp.data.repository.AppRepository
import com.google.common.truth.Truth.assertThat
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Test
import org.amshove.kluent.*

import org.mockito.kotlin.*
import retrofit2.Response
import java.io.IOException
import java.lang.NullPointerException
import java.lang.RuntimeException


@ExperimentalCoroutinesApi
class RepositoryTest {
    //class to be tested
    private lateinit var appRepository: AppRepository

    //courputine dispatcher
    private var testDispatcher = TestCoroutineDispatcher()

    private val apiService = mock<ApiService>()

    private val newsDao = mock<NewDao>()

    @Before
    fun setUp(){
       appRepository = AppRepository(apiService, newsDao)
    }


    fun `test that getOrganization emit successfully`() = runBlocking{
        val response = GetNewsResponse(response,"success", 34)

        //mock api service
        apiService.stub {
           onBlocking {getNewByHeadLines("122344", "us")  }.doReturn(response)
        }

        //test and verify
        appRepository.getNewHeadlines("122344","us").test {
             assertThat(awaitItem()).isEqualTo(ResultManager.Loading(true))  //emits loading
             assertThat(awaitItem()).isEqualTo(ResultManager.Success(response)) //emits success
            assertThat(awaitItem()).isEqualTo(ResultManager.Loading(false)) //completion
            cancelAndConsumeRemainingEvents()

        }

    }


    @Test
    fun `test that getOrganization emit failure`() = runBlocking{
        val response = GetNewsResponse(response,"success", 34)

        whenever(apiService.getNewByHeadLines("123456", "us")).thenThrow(Throwable("exception"))
             //Test and Verify

            val flow: Flow<ResultManager<GetNewsResponse>> = appRepository.getNewHeadlines("123456", "us")

            flow.test {
                assertThat(awaitItem()).isEqualTo(ResultManager.Loading(true))
                assertEquals(ResultManager.Failure(Throwable("exception")), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
//            assertThat(awaitItem()).isEqualTo(ResultManager.Loading(true))  //emits loading
//            assertEquals(ResultManager.Failure(Throwable()), awaitError())
//           awaitComplete()
        //mock api service
//        apiService.stub {
//            onBlocking {getNewByHeadLines("122344", "us")  }.doReturn(response)
//        }
//        appRepository.getNewHeadlines("","").test{
//            assertThat(awaitItem()).isEqualTo(ResultManager.Loading(true))
//            assertThat(awaitItem()).isEqualTo(ResultManager.Success(null))
//            assertThat(awaitItem()).isEqualTo(ResultManager.Loading(false))
           // assertThat(awaitItem()).isEqualTo(ResultManager.Failure(NullPointerException()))




          //  assertThat()
//            assertThat(awaitItem()).isEqualTo(ResultManager.Loading(true))
//            ResultManager.Failure(this.awaitError()).`should be`(this.awaitItem())
//            assertThat(awaitItem()).isEqualTo(ResultManager.Loading(false))
//            cancelAndConsumeRemainingEvents()
            // Mock
//            val apiService = mock<ApiService>()
//            whenever(apiService.userDetails()) doAnswer {
//                throw IOException()
//            }
            // Test and Verify
//            val repository = UsersRepository(apiService)
//            val flow: Flow<UserDetails> = repository.getUserDetails()
//
//            flow.test {
//                assertThat(
//                    expectError(),
//                    instanceOf(IOException::class.java)
//                )
//            }
//            assertThat(awaitItem()).isEqualTo(ResultManager.Loading(true))  //emits loading
//            assertEquals(ResultManager.Failure(Throwable()), awaitError())
//           awaitComplete()

        }

    }



    private val response = listOf(NewsEntity(2,"mine", "mineie","your","non3", Source("34", "youi"),"jjjj","https", "httpl"))


////test and verify
//val flow = appRepository.getNewHeadlines("122344", "us")
//
//flow.collect {it ->
//    ResultManager.Failure(Throwable()).`should not be`(it)
//
//}
