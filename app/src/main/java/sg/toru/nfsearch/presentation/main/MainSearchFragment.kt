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

    private var binding:FragmentMainSearchBinding? = null

    override fun initDependencyInjection() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)

        (requireActivity() as MainActivity).mainViewModel.nameLiveData.observe(viewLifecycleOwner, Observer {
            Log.e("Toru", "MainSearchFragment!! $it")
        })

        (requireActivity() as MainActivity).mainViewModel.trigger.observe(viewLifecycleOwner, Observer {
            Log.e("Toru", "MainSearchFragment return:: $it")
        })
    }

    companion object {
        fun getInstance() = MainSearchFragment()
    }
}