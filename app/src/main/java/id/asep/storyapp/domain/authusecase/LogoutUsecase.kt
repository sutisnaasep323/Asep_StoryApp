package id.asep.storyapp.domain.authusecase

import id.asep.storyapp.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUsecase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() {
        repository.logout()
    }

}