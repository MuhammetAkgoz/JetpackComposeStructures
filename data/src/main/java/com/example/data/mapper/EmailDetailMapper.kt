package com.example.data.mapper

import com.example.core.functional.mapOrDefault
import com.example.core.functional.orDefault
import com.example.core.mapper.ResultMapper
import com.example.data.response.emaldetails.EmailDetailsDto
import com.example.data.response.emaldetails.RecipientInfo
import com.example.data.response.emaldetails.SenderInfo
import com.example.domain.model.emaildetails.EmailDetailsModel
import com.example.domain.model.emaildetails.FileInfo
import com.example.domain.model.emaildetails.RecipientModel
import com.example.domain.model.emaildetails.SenderInfoModel
import javax.inject.Inject
import kotlin.collections.first

class EmailDetailsMapper @Inject constructor() :
    ResultMapper<ArrayList<EmailDetailsDto>, EmailDetailsModel> {
    override fun map(input: ArrayList<EmailDetailsDto>): EmailDetailsModel =
        input.first().toEmailDetailsModel()

    private fun EmailDetailsDto.toEmailDetailsModel(): EmailDetailsModel {
        return EmailDetailsModel(
            id = id,
            from = payload.senderInfo.toSenderInfoModel(),
            to = payload.to.toRecipientModel(),
            cc = payload.cc.toRecipientModel(),
            bcc = payload.bcc.toRecipientModel(),
            subject = payload.subject.orEmpty(),
            htmlBody = body?.html ?: body?.text ?: "",
            date = payload.date.orEmpty(),
            isImportant = isImportant.orDefault(),
            isStarred = labels.contains("Starred"),
            isPromotional = isPromotional.orDefault(),
            fileInfo =
                payload.attachments.mapOrDefault(emptyList()) {
                    FileInfo(
                        filename = it!!.filename.orEmpty(),
                        mimeType = it.mimeType.orEmpty(),
                        size = it.size ?: 0L,
                        downLoadUrl = it.downloadUrl
                    )
                },
            labels =
                labels.mapOrDefault(emptyList()) {
                    it!!
                }
        )
    }

    private fun SenderInfo.toSenderInfoModel(): SenderInfoModel {
        return SenderInfoModel(
            email = email,
            name = name,
            profileImage = profileImage
        )
    }

    private fun List<RecipientInfo>?.toRecipientModel(): List<RecipientModel> {
        return this.mapOrDefault {
            RecipientModel(
                email = it.email,
                name = it.name
            )
        }
    }
}