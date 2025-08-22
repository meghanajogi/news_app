package com.example.domain.usecase
import com.example.domain.model.NewsItem
import com.example.domain.repository.NewsRepository
import com.example.domain.utils.Resource

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetItemDetailsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(id: Int): Flow<Resource<NewsItem>>{
        return repository.getItemById(id)
    }
}
