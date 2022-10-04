package id.asep.storyapp.ui.storyscreen

import id.asep.storyapp.domain.model.Stories
import id.asep.storyapp.domain.model.User

data class HomeUiState(
    val listData: List<Stories> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val userLogin: User? = null
)
