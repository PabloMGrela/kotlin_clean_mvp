package com.grela.clean

import android.app.Activity
import android.content.Context
import com.grela.data.DataModules
import com.grela.domain.DomainModules
import com.grela.presentation.MainPresenter
import com.grela.presentation.MainViewTranslator
import com.grela.presentation.PresentationModules
import com.grela.remote_datasource.RemoteModules
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

object AppModules {
    inline fun <reified T : Any> Activity.injectActivity(): Lazy<T> = inject { parametersOf(this) }

    val modules = listOf(
        DataModules.modules,
        DomainModules.modules,
        RemoteModules.modules,
        PresentationModules.modules,
        module {
            factory { (view: Context) -> MainPresenter(view as MainViewTranslator, get(), get()) }
        }
    )
}