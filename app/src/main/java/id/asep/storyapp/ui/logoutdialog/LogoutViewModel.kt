package id.asep.storyapp.ui.logoutdialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.asep.storyapp.domain.authusecase.GetCurrentUserUsecase
import id.asep.storyapp.domain.authusecase.LogoutUsecase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel @Inject constructor(
    private val logoutUsecase: LogoutUsecase,
    getCurrentUserUsecase: GetCurrentUserUsecase
) : ViewModel() {

    val user = getCurrentUserUsecase.invoke()

    fun logout() = viewModelScope.launch {
        logoutUsecase.invoke()
    }

}