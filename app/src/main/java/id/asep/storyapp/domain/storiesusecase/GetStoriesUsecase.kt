package id.asep.storyapp.domain.storiesusecase

import id.asep.storyapp.domain.repository.StoryRepository
import javax.inject.Inject

class GetStoriesUsecase @Inject constructor(
    private val storyRepository: StoryRepository
) {
    suspend operator fun invoke(token: String) = storyRepository.getStories(token)

}