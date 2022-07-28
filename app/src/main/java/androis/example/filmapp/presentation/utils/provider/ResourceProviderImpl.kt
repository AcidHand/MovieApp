package androis.example.filmapp.presentation.utils.provider

import android.content.res.Resources

class ResourceProviderImpl(private val resources: Resources) : ResourceProvider {

    override fun getString(res: Int, vararg args: Any): String {
        return resources.getString(res, *args)
    }
}