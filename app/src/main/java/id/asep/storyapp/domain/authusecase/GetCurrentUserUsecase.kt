package id.asep.storyapp.domain.authusecase

import id.asep.storyapp.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUserUsecase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke() = authRepository.getCurrentUser()
}