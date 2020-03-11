package sg.toru.nfsearch.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import sg.toru.nfsearch.R
import sg.toru.nfsearch.app.NFApp
import sg.toru.nfsearch.databinding.ActivityMainBinding
import sg.toru.nfsearch.domain.di.MainDomainModule
import sg.toru.nfsearch.domain.viewmodel.MainViewModel
import sg.toru.nfsearch.presentation.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private lateinit var binding:ActivityMainBinding

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun initDependencyInjection() {
        (application as NFApp).appComponent()
            .mainDomainComponent(MainDomainModule())
            .injectTo(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initView()

        mainViewModel.nameLiveData.value = "michael jordan"
        mainViewModel.trigger.observe(this, Observer {
            Log.e("Toru", "return:: $it")
        })
    }

    private fun initView() {
        binding.viewPager.adapter = MainPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = "POSITION $position"
        }.attach()
    }
}