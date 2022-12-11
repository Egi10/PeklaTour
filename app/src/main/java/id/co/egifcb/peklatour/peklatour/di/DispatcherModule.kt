package id.co.egifcb.peklatour.peklatour.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatcherModule = module {
    single(named(Dispatcher.IO)) {
        Dispatchers.IO
    }

    single(named(Dispatcher.Default)) {
        Dispatchers.Default
    }

    single(named(Dispatcher.Main)) {
        Dispatchers.Main
    }
}

enum class Dispatcher {
    IO,
    Main,
    Default
}