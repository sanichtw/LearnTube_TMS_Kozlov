package com.example.learntube.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import com.example.learntube.domain.models.Thumbnails

@Entity
internal data class ThumbnailsEntity(
    @Embedded(prefix = "default_")
    var defaultSize: ThumbnailEntity?,

    @Embedded(prefix = "medium_")
    var mediumSize: ThumbnailEntity?,

    @Embedded(prefix = "high_")
    var highSize: ThumbnailEntity?
) {
    companion object {
        fun empty(): ThumbnailsEntity = ThumbnailsEntity(
            defaultSize = ThumbnailEntity.empty(),
            mediumSize = ThumbnailEntity.empty(),
            highSize = ThumbnailEntity.empty()
        )
    }
}

internal fun ThumbnailsEntity.mapToThumbnailsDomain() = Thumbnails(
    defaultSize = defaultSize?.mapToThumbnailDomain(),
    mediumSize = mediumSize?.mapToThumbnailDomain(),
    highSize = highSize?.mapToThumbnailDomain(),
)



