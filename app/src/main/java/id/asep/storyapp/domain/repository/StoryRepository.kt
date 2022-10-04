package id.asep.storyapp.domain.repository

import id.asep.storyapp.domain.model.Stories
import id.asep.storyapp.util.MyResource
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import okhttp3.ResponseBody
import java.io.File


interface StoryRepository {

    suspend fun getStories(token: String): Flow<MyResource<List<Stories>>>

    suspend fun addStory(
        deskripsi: RequestBody?,
        image: File?
    ): Flow<MyResource<ResponseBody>>

}