package com.grela.clean

import android.app.Application
import android.content.Context
import androidx.lifecycle.LifecycleObserver
import androidx.test.espresso.IdlingRegistry
import androidx.test.runner.AndroidJUnitRunner
import com.grela.domain.interactor.Invoker
import com.grela.domain.interactor.UseCaseInvoker
import org.junit.After
import org.junit.Before
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module

open class BaseUITestRunner : AndroidJUnitRunner() {
    private val counter = CounterIdleResource("Espresso")

    @Before
    fun activateWaitForInvoker() {
        loadKoinModules(module {
            factory<Invoker>(override = true) { UseCaseInvoker(idleNotifier = counter) }
        }
        )

        IdlingRegistry.getInstance().register(counter)
    }

    @After
    fun disableWaitForInvoker() {
        IdlingRegistry.getInstance().unregister(counter)
    }

    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(classLoader: ClassLoader, className: String, context: Context): Application =
        super.newApplication(classLoader, MockApplication::class.java.name, context)
}

class MockApplication : Application(), LifecycleObserver {

    companion object {
        private var mInstance: MockApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        startKoin {
            androidContext(this@MockApplication)
            modules(AppModules.modules)
        }

    }
}