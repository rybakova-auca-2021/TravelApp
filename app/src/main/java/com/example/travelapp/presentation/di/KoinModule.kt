package com.example.travelapp.presentation.di

import com.example.travelapp.data.api.AuthInterface
import com.example.travelapp.data.api.DataInterface
import com.example.travelapp.data.api.ProfileInterface
import com.example.travelapp.data.api.WeatherInterface
import com.example.travelapp.data.constants.Constant.Companion.BASE_URL
import com.example.travelapp.data.repository.AuthRepository
import com.example.travelapp.data.repository.AuthRepositoryImpl
import com.example.travelapp.data.repository.MainRepository
import com.example.travelapp.data.repository.MainRepositoryImpl
import com.example.travelapp.data.repository.WeatherRepository
import com.example.travelapp.data.repository.WeatherRepositoryImpl
import com.example.travelapp.presentation.viewModel.auth.CodeVerificationViewModel
import com.example.travelapp.presentation.viewModel.auth.LoginViewModel
import com.example.travelapp.presentation.viewModel.auth.NewPasswordViewModel
import com.example.travelapp.presentation.viewModel.auth.RegisterViewModel
import com.example.travelapp.presentation.viewModel.auth.ResetPasswordViewModel
import com.example.travelapp.presentation.viewModel.main.GetMustVisitListViewModel
import com.example.travelapp.presentation.viewModel.main.GetMustVisitPlaceDetailViewModel
import com.example.travelapp.presentation.viewModel.main.GetPackageDetailViewModel
import com.example.travelapp.presentation.viewModel.main.GetPackagesViewModel
import com.example.travelapp.presentation.viewModel.main.GetPopularDetailViewModel
import com.example.travelapp.presentation.viewModel.main.GetPopularListViewModel
import com.example.travelapp.presentation.viewModel.weather.GetWeatherViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext
import org.koin.core.qualifier.named
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
    single(named("mainApi")) {
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

    single(named("weatherApi")) {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>(named("mainApi")).create(AuthInterface::class.java) }
    single { get<Retrofit>(named("mainApi")).create(ProfileInterface::class.java) }
    single { get<Retrofit>(named("mainApi")).create(DataInterface::class.java) }
    single { get<Retrofit>(named("weatherApi")).create(WeatherInterface::class.java) }
}


val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<MainRepository> { MainRepositoryImpl(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel { CodeVerificationViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { NewPasswordViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { ResetPasswordViewModel(get()) }
    viewModel { GetPopularListViewModel(get()) }
    viewModel { GetPopularDetailViewModel(get()) }
    viewModel { GetMustVisitListViewModel(get()) }
    viewModel { GetMustVisitPlaceDetailViewModel(get()) }
    viewModel { GetPackagesViewModel(get()) }
    viewModel { GetPackageDetailViewModel(get()) }
    viewModel { GetWeatherViewModel(get()) }
}