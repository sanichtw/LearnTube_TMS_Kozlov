package com.example.learntube.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import com.example.learntube.domain.models.Thumbnails

@Entity
internal data class ThumbnailsEntity(
    @Embedded(prefix = "default_")
    var defaultSize: ThumbnailEntity? = null,

    @Embedded(prefix = "medium_")
    var mediumSize: ThumbnailEntity? = null,

    @Embedded(prefix = "high_")
    var highSize: ThumbnailEntity? = null
)

internal fun ThumbnailsEntity.mapToThumbnailsDomain() = Thumbnails(
    defaultSize = defaultSize?.mapToThumbnailDomain(),
    mediumSize = mediumSize?.mapToThumbnailDomain(),
    highSize = highSize?.mapToThumbnailDomain(),
)



