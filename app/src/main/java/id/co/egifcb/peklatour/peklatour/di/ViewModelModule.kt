package id.co.egifcb.peklatour.peklatour.di

import id.co.egifcb.peklatour.peklatour.ui.auth.login.LoginViewModel
import id.co.egifcb.peklatour.peklatour.ui.auth.register.RegisterViewModel
import id.co.egifcb.peklatour.peklatour.ui.home.HomeViewModel
import id.co.egifcb.peklatour.peklatour.ui.listtour.ListTourViewModel
import id.co.egifcb.peklatour.peklatour.ui.order.OrderViewModel
import id.co.egifcb.peklatour.peklatour.ui.profile.ProfileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::OrderViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::ListTourViewModel)
}