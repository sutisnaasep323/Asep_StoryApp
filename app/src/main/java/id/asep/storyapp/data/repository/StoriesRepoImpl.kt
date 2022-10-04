package id.asep.storyapp.data.repository

import id.asep.storyapp.data.datastore.UserPreff
import id.asep.storyapp.data.remote.RemoteDataSource
import id.asep.storyapp.domain.model.Stories
import id.asep.storyapp.domain.repository.StoryRepository
import id.asep.storyapp.util.MyResource
import id.asep.storyapp.util.map
import id.asep.storyapp.util.toStories
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import okhttp3.RequestBody
import okhttp3.ResponseBody
import java.io.File
import javax.inject.Inject

class StoriesRepoImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val userPreff: UserPreff
) : StoryRepository {
    override suspend fun getStories(token: String): Flow<MyResource<List<Stories>>> {

        return remoteDataSource.fetchAllStory(token).map {
            it.map { stories ->
                stories.toStories()
            }
        }

    }

    @OptIn(FlowPreview::class)
    override suspend fun addStory(
        deskripsi: RequestBody?,
        image: File?
    ): Flow<MyResource<ResponseBody>> {
        return userPreff.user.mapNotNull { it }.flatMapConcat {
            remoteDataSource.addStories(it.token, deskripsi, image)
        }
    }


}