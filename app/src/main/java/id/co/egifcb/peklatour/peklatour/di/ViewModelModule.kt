package id.co.egifcb.peklatour.peklatour.di

import id.co.egifcb.peklatour.peklatour.ui.auth.login.LoginViewModel
import id.co.egifcb.peklatour.peklatour.ui.auth.register.RegisterViewModel
import id.co.egifcb.peklatour.peklatour.ui.home.HomeViewModel
import id.co.egifcb.peklatour.peklatour.ui.listtour.ListTourViewModel
import id.co.egifcb.peklatour.peklatour.ui.order.OrderViewModel
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

    viewModel {
        OrderViewModel(get(), get())
    }

    viewModel {
        HomeViewModel(get())
    }

    viewModel {
        ListTourViewModel(get())
    }
}