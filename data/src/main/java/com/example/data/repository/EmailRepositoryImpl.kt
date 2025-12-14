package com.example.data.repository

import com.example.core.error.Failure
import com.example.core.functional.Either
import com.example.data.mapper.EmailDetailsMapper
import com.example.data.mapper.EmailListMapper
import com.example.data.remote.api.ApiService
import com.example.data.remote.handler.safeApiCall
import com.example.domain.model.emaillist.EmailListItemModel
import com.example.domain.repository.EmailRepository
import javax.inject.Inject

class EmailRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val emailListMapper: EmailListMapper,
    private val emailDetailsMapper: EmailDetailsMapper
) : EmailRepository {
    override suspend fun getEmailList(): Either<Failure, List<EmailListItemModel>> = safeApiCall(
        apiCall = { apiService.getEmailList() },
        mapper = { emailListMapper.map(it) }
    )

    override suspend fun getEmailDetails() = safeApiCall(
        apiCall = { apiService.getEmailDetail() },
        mapper = { emailDetailsMapper.map(it) }
    )
}
