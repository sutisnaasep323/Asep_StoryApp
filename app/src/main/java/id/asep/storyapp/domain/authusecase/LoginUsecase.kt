package id.asep.storyapp.domain.authusecase

import id.asep.storyapp.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUsecase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(username: String, password: String) =
        repository.login(username, password)

}