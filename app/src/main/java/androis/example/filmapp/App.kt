package androis.example.filmapp

import android.app.Application
import androis.example.filmapp.domain.di.useCaseModule
import androis.example.filmapp.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
           // androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                listOf(
                    useCaseModule,
                    presentationModule
                )
            )
        }
    }
}