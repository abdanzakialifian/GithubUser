package com.application.zaki.githubuser.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.zaki.githubuser.domain.usecase.IGithubUseCase
import com.application.zaki.githubuser.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val githubUseCase: IGithubUseCase) : ViewModel() {
    fun getListUser() = githubUseCase.getListUsers().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = NetworkResult.Loading(null)
    )

    fun getUser(query: String) = githubUseCase.getUsers(query).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = NetworkResult.Loading(null)
    )
}