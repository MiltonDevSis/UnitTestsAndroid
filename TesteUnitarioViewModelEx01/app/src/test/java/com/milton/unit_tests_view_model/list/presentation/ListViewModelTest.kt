package com.milton.unit_tests_view_model.list.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.milton.unit_tests_view_model.list.data.Repository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class ListViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    //repositorio mocado
    private val repository = mockk<Repository>()
    private val onDataLoadedObserver = mockk<Observer<List<String>>>(relaxed = true)

    @Test
    fun `when view model fetchs data then it should  call the reppository`(){
        // instancia viewModel
        val viewModel = inicializaViewModel()
        val mockedList = listOf("maça", "pera")

        every { repository.getData() } returns mockedList

        viewModel.fetchData()

        verify { repository.getData() }
        verify { onDataLoadedObserver.onChanged(mockedList) }
    }

    private fun inicializaViewModel(): ListViewModel{
        val viewModel =  ListViewModel(repository)
        viewModel.onDataLoaded.observeForever(onDataLoadedObserver)
        return viewModel
    }
}