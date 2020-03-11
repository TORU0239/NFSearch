package sg.toru.nfsearch.presentation.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import sg.toru.nfsearch.R
import sg.toru.nfsearch.app.NFApp
import sg.toru.nfsearch.databinding.ActivityMainBinding
import sg.toru.nfsearch.domain.di.MainDomainModule
import sg.toru.nfsearch.domain.viewmodel.MainViewModel
import sg.toru.nfsearch.presentation.BaseActivity
import sg.toru.nfsearch.presentation.extension.hideKeyboard
import sg.toru.nfsearch.presentation.extension.reduceDragSensitivity
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

        binding.edMainSearch.setOnEditorActionListener { v, _, _ ->
            mainViewModel.imageQueryLiveData.value = v.text.toString()
            binding.edMainSearch.hideKeyboard()
            true
        }
    }

    private fun initView() {
        binding.viewPager.adapter = MainPagerAdapter(this)
        binding.viewPager.isUserInputEnabled = true
        binding.viewPager.reduceDragSensitivity()
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Image Search"
                else -> tab.text = "Google Search"
            }
        }.attach()
    }

    override fun onBackPressed() {
        mainViewModel.stop()
        super.onBackPressed()
    }
}