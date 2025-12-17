package com.example.data.mapper

import com.example.core.functional.orDefault
import com.example.core.mapper.ResultMapper
import com.example.data.response.emaillist.EmailListItemDto
import com.example.domain.model.emaillist.EmailListItemModel
import javax.inject.Inject


class EmailListMapper @Inject constructor() :
    ResultMapper<List<EmailListItemDto>, List<EmailListItemModel>> {
    override fun map(input: List<EmailListItemDto>): List<EmailListItemModel> =
        input.filter {
            it.id != null && it.payload?.from != null
        }.map {
            it.toModel()
        }.sortedBy {
            it.date
        }.reversed()

    private fun EmailListItemDto.toModel() =
        EmailListItemModel(
            id = id!!,
            from = payload?.from!!,
            subject = payload.subject.orEmpty(),
            profileImage = payload.profileImage,
            snippet = snippet.orEmpty(),
            date = payload.date.orEmpty(),
            isImportant = isImportant.orDefault(),
            isStarred = isImportant.orDefault(),
            isPromotional = isPromotional.orDefault()
        )
}