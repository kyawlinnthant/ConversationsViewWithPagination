package klt.mdy.conversationviewwithpagination.di

import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import klt.mdy.conversationviewwithpagination.data.ApiService
import klt.mdy.conversationviewwithpagination.data.Repository
import klt.mdy.conversationviewwithpagination.data.RepositoryImpl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object
AppModule {
    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(
                OkHttpClient.Builder().addInterceptor(
                    OkHttpProfilerInterceptor()
                ).build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesRepository(apiService: ApiService): Repository {
        return RepositoryImpl(
            api = apiService
        )
    }
}