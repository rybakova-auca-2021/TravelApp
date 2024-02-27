package com.example.travelapp.presentation.di

import com.example.travelapp.data.api.AuthInterface
import com.example.travelapp.data.api.ProfileInterface
import com.example.travelapp.data.constants.Constant.Companion.BASE_URL
import com.example.travelapp.data.repository.AuthRepository
import com.example.travelapp.data.repository.AuthRepositoryImpl
import com.example.travelapp.presentation.viewModel.auth.CodeVerificationViewModel
import com.example.travelapp.presentation.viewModel.auth.LoginViewModel
import com.example.travelapp.presentation.viewModel.auth.NewPasswordViewModel
import com.example.travelapp.presentation.viewModel.auth.RegisterViewModel
import com.example.travelapp.presentation.viewModel.auth.ResetPasswordViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    GlobalContext.loadKoinModules(
        listOf(
            networkModule,
            repositoryModule,
            viewModelModule
        )
    )
}

val networkModule = module {
    single {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(AuthInterface::class.java) }
    single { get<Retrofit>().create(ProfileInterface::class.java) }

}

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel { CodeVerificationViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { NewPasswordViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { ResetPasswordViewModel(get()) }
}