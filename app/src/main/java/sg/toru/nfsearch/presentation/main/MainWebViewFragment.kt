package sg.toru.nfsearch.presentation.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import sg.toru.nfsearch.R
import sg.toru.nfsearch.app.NFApp
import sg.toru.nfsearch.databinding.FragmentMainWebViewBinding
import sg.toru.nfsearch.domain.di.MainDomainModule
import sg.toru.nfsearch.domain.viewmodel.MainViewModel
import sg.toru.nfsearch.presentation.BaseFragment
import javax.inject.Inject

class MainWebViewFragment : BaseFragment(R.layout.fragment_main_web_view) {
    @Inject
    lateinit var viewModel:MainViewModel

    private var binding:FragmentMainWebViewBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)
        binding?.let {}
    }

    override fun initDependencyInjection() {
        (activity?.application as NFApp).appComponent()
            .mainDomainComponent(MainDomainModule())
            .injectTo(this)
    }

    companion object {
        fun getInstance() = MainWebViewFragment()
    }
}
