package sg.toru.nfsearch.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator

import sg.toru.nfsearch.R
import sg.toru.nfsearch.app.NFApp
import sg.toru.nfsearch.databinding.FragmentMainBinding
import sg.toru.nfsearch.databinding.FragmentMainSearchBinding
import sg.toru.nfsearch.domain.di.MainDomainModule
import sg.toru.nfsearch.domain.viewmodel.MainViewModel
import sg.toru.nfsearch.presentation.BaseFragment
import javax.inject.Inject

class MainFragment : BaseFragment(R.layout.fragment_main) {

    @Inject
    lateinit var mainViewModel: MainViewModel

    private var binding: FragmentMainBinding? = null

    override fun initDependencyInjection() {
//        (activity?.application as NFApp).appComponent()
//            .mainDomainComponent(MainDomainModule())
//            .injectTo(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)
        initView()
        mainViewModel.nameLiveData.value = "michael jordan"
        mainViewModel.trigger.observe(viewLifecycleOwner, Observer {
            Log.e("Toru", "return:: $it")
        })
    }

    private fun initView() {
        binding?.viewPager?.adapter = MainPagerAdapter(requireActivity())
        TabLayoutMediator(binding?.tabLayout!!, binding?.viewPager!!) { tab, position ->
            tab.text = "POSITION $position"
        }.attach()
    }

}
