package com.example.newsgate.news.data.local.appDatabase

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsgate.news.data.local.entities.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticlesDao {

    @Query("select * from articles")
    fun getAllNormalArticles(): PagingSource<Int, ArticleEntity>

    @Query("select * from articles where isHeadline =:isHeadline")
    fun getHeadlinesArticles(isHeadline: Boolean): Flow<List<ArticleEntity>>

    @Query("select * from articles where id = :id")
    fun getArticleById(id: Long): Flow<ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<ArticleEntity>)

    @Query("delete from articles")
    fun deleteAllArticles()
}
