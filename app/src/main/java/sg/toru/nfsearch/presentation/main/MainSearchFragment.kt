package sg.toru.nfsearch.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import sg.toru.nfsearch.R
import sg.toru.nfsearch.app.NFApp
import sg.toru.nfsearch.databinding.FragmentMainSearchBinding
import sg.toru.nfsearch.domain.di.MainDomainModule
import sg.toru.nfsearch.domain.viewmodel.MainViewModel
import sg.toru.nfsearch.presentation.BaseFragment
import javax.inject.Inject

class MainSearchFragment : BaseFragment() {

    private lateinit var binding:FragmentMainSearchBinding

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun initDependencyInjection() {
        (requireActivity().application as NFApp).appComponent()
            .mainDomainComponent(MainDomainModule())
            .injectTo(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_search, container, false)
        (requireActivity() as MainActivity).imageQueryLiveData.observe(viewLifecycleOwner, Observer {
            Log.e("Toru", "MainSearchFragment!! $it")
            mainViewModel.request(it, 1)
        })
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.rcvMainSearch.adapter = MainSearchAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.successResponse.observe(viewLifecycleOwner, Observer {
            Log.e("Toru", "MainSearchFragment success size:: ${it.size}")
        })
        mainViewModel.failedResponse.observe(viewLifecycleOwner, Observer {
            Log.e("Toru", "MainSearchFragment failed message $it")
        })
    }

    companion object {
        fun getInstance() = MainSearchFragment()
    }
}