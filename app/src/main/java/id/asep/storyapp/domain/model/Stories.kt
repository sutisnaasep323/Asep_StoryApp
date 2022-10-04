package id.asep.storyapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stories(
    val photoUrl: String,
    val createdAt: String,
    val name: String,
    val description: String,
    val lon: Double?,
    val id: String,
    val lat: Double?
) : Parcelable