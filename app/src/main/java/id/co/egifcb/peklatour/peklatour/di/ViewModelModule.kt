package id.co.egifcb.peklatour.peklatour.di

import id.co.egifcb.peklatour.peklatour.ui.auth.login.LoginViewModel
import id.co.egifcb.peklatour.peklatour.ui.auth.register.RegisterViewModel
import id.co.egifcb.peklatour.peklatour.ui.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        LoginViewModel(get())
    }
    
    viewModel {
        RegisterViewModel(get())
    }

    viewModel {
        ProfileViewModel(get())
    }
}