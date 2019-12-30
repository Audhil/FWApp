package com.example.fwapp.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.fwapp.base.BaseTest
import com.example.fwapp.model.api.UserDetail
import com.example.fwapp.ui.main.MainRepo
import io.mockk.*
import io.reactivex.Flowable
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainRepoTest : BaseTest() {

    @get:Rule
    val instantExecutorRule by lazy {
        InstantTaskExecutorRule()
    }

    @get:Rule
    val initSetUpTestRule by lazy {
        InitSetUpTestRule()
    }

    private var mainRepo: MainRepo? = null

    @Before
    fun `set up`() {
        mainRepo = spyk(
            MainRepo(
                api,
                errorLiveData,
                testScheduler
            )
        )
    }

    @After
    fun `tear down`() {
        mainRepo = null
    }

    @Test
    fun `fetchUsers happy case list not empty`() {
        val listOfUserDetails = arrayListOf<UserDetail>()
        listOfUserDetails.add(UserDetail())
        every { api.grabUsers() } returns Flowable.just(listOfUserDetails)

        mainRepo?.grabUsersFromServer()

        val arg1 = slot<List<UserDetail>>()
        val arg2 = slot<String>()
        verify(exactly = 1) {
            mainRepo?.onSuccess(
                capture(arg1), capture(arg2)
            )
        }
        assert(arg1.captured == listOfUserDetails)
        assert(arg2.captured == "TAG")
        assert(mainRepo?._userListMutableLiveData?.value != null)
        assert(mainRepo?._userListMutableLiveData?.value == listOfUserDetails)
    }

    @Test
    fun `fetchUsers happy case list empty`() {
        val listOfUserDetails = listOf<UserDetail>()
        every { api.grabUsers() } returns Flowable.just(listOfUserDetails)

        mainRepo?.grabUsersFromServer()

        val arg1 = slot<List<UserDetail>>()
        val arg2 = slot<String>()
        verify(exactly = 1) {
            mainRepo?.onSuccess(
                capture(arg1), capture(arg2)
            )
        }
        assert(arg1.captured == listOfUserDetails)
        assert(arg2.captured == "TAG")
        assert(mainRepo?._userListMutableLiveData?.value == null)
    }
}