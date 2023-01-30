package com.application.zaki.githubuser.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.application.zaki.githubuser.domain.model.ListUsers
import com.application.zaki.githubuser.domain.interfaces.IGithubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val githubUseCase: IGithubUseCase) : ViewModel() {
    fun getListUsers(): StateFlow<PagingData<ListUsers>> = githubUseCase.getListUsers()
        .cachedIn(viewModelScope)
        .stateIn(
        initialValue = PagingData.empty(),
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(3000L)
    )

    fun searchUser(query: String): StateFlow<PagingData<ListUsers>> =
        githubUseCase.getUsers(query)
            .cachedIn(viewModelScope)
            .stateIn(
            initialValue = PagingData.empty(),
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(3000L)
        )
}