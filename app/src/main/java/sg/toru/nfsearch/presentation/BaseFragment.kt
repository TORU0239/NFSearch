package sg.toru.nfsearch.presentation

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseFragment(id:Int): Fragment(id) {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDependencyInjection()
    }

    abstract fun initDependencyInjection()
}