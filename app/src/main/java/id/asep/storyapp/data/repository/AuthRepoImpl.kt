package id.asep.storyapp.data.repository

import id.asep.storyapp.data.datastore.UserPreff
import id.asep.storyapp.data.remote.RemoteDataSource
import id.asep.storyapp.data.remote.model.LoginResponse
import id.asep.storyapp.domain.model.User
import id.asep.storyapp.domain.repository.AuthRepository
import id.asep.storyapp.util.MyResource
import id.asep.storyapp.util.toUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val dataSource: RemoteDataSource,
    private val userPreff: UserPreff
) : AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Flow<MyResource<LoginResponse>> {
        val loginResponse = dataSource.login(email, password)
        loginResponse.map {
            it.data?.loginResult?.toUser()
        }.collectLatest {
            if (it != null) {
                saveUser(it)
            }
        }


        return loginResponse
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ) = dataSource.register(name, email, password)

    override suspend fun logout() {
        userPreff.clear()
    }

    override fun getCurrentUser(): Flow<User?> = userPreff.user
    override fun isLoggedIn() = userPreff.isLogin


    override suspend fun saveUser(user: User) {
        userPreff.save(user)
    }

}