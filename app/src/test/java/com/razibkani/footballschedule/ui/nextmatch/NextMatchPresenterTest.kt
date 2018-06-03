package com.razibkani.footballschedule.ui.nextmatch

import com.razibkani.footballschedule.data.DataManager
import com.razibkani.footballschedule.data.model.Event
import com.razibkani.footballschedule.data.model.FootballEventResponse
import com.razibkani.footballschedule.utils.TestCoroutineContextProvider
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest {

    @Mock
    private
    lateinit var mvpView: NextMatchMvpView

    @Mock
    private
    lateinit var dataManager: DataManager

    private lateinit var presenter: NextMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchPresenter(dataManager, TestCoroutineContextProvider())
    }

    @Test
    fun getEvents() {
        val events: List<Event> = listOf()
        val response = FootballEventResponse(events)
        runBlocking {
            Mockito.`when`(dataManager.getFootballNextEvent()).thenReturn(response)
        }

        presenter.attachView(mvpView)

        presenter.getEvents()

        Mockito.verify(mvpView)?.showLoading()

        if (events.isNotEmpty()) {
            Mockito.verify(mvpView)?.updateData(events)
        } else {
            Mockito.verify(mvpView)?.showErrorMessage("Event Kosong")
        }

        Mockito.verify(mvpView)?.hideLoading()
    }
}