package com.example.data.remote.dto

import com.example.domain.model.NewsItem

data class NewsItemDto(val id:Int?,val userId: Int?, val title: String?,val body: String?)

fun NewsItemDto.toDomain(): NewsItem {
    return NewsItem (id, userId, title,body)
}