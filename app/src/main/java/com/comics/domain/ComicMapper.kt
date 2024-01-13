package com.comics.domain

import com.comics.data.ItemModel
import com.comics.data.ResultModel

interface ComicMapper {
    fun mapToItem(resultModel: ResultModel): Item
}

class ComicMapperImpl : ComicMapper {

    override fun mapToItem(resultModel: ResultModel): Item {
        resultModel.apply {
            return Item(
                image = "${this.thumbnail.path}.${this.thumbnail.extension}",
                title = this.title,
                subtitle = this.description ?: "Sem descricao"
            )
        }
    }
}