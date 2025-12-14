package com.example.domain.repository

import com.example.core.error.Failure
import com.example.core.functional.Either
import com.example.domain.model.emaildetails.EmailDetailsModel
import com.example.domain.model.emaillist.EmailListItemModel

interface EmailRepository {
    suspend fun getEmailList(): Either<Failure, List<EmailListItemModel>>
    suspend fun getEmailDetails(): Either<Failure, EmailDetailsModel>
}
