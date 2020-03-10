package sg.toru.nfsearch.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import sg.toru.nfsearch.R
import sg.toru.nfsearch.app.NFApp
import sg.toru.nfsearch.databinding.FragmentMainSearchBinding
import sg.toru.nfsearch.domain.di.MainDomainModule
import sg.toru.nfsearch.domain.viewmodel.MainViewModel
import sg.toru.nfsearch.presentation.BaseFragment
import javax.inject.Inject

class MainSearchFragment : BaseFragment(R.layout.fragment_main_search) {

    @Inject
    lateinit var mainViewModel:MainViewModel

    private var binding:FragmentMainSearchBinding? = null

    override fun initDependencyInjection() {
        (activity?.application as NFApp).appComponent()
            .mainDomainComponent(MainDomainModule())
            .injectTo(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)
    }

    companion object {
        fun getInstance() = MainSearchFragment()
    }
}