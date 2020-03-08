package sg.toru.nfsearch.presentation.main

import sg.toru.nfsearch.R
import sg.toru.nfsearch.app.NFApp
import sg.toru.nfsearch.domain.di.MainDomainModule
import sg.toru.nfsearch.domain.viewmodel.MainViewModel
import sg.toru.nfsearch.presentation.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModel:MainViewModel

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initDependencyInjection() {
        (application as NFApp).appComponent()
            .mainDomainComponent(MainDomainModule())
            .injectTo(this)
    }
}