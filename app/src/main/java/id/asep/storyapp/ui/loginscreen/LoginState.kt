package id.asep.storyapp.ui.loginscreen

import id.asep.storyapp.domain.model.User

data class LoginState(
    private val user: User? = null,
    val isloading: Boolean = false,
    val error: String? = null
) {
    val isLoggedIn: Boolean get() = user != null
}