package sg.toru.nfsearch.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebSettings.PluginState
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import sg.toru.nfsearch.R
import sg.toru.nfsearch.databinding.FragmentMainWebViewBinding
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
        binding?.let {
            it.mainWebView.settings.javaScriptEnabled = true
            it.mainWebView.isNestedScrollingEnabled = true
        }

        (requireActivity() as MainActivity).mainViewModel.imageQueryLiveData.observe(viewLifecycleOwner, Observer {
            Log.e("Toru", "MainWebViewFragment!! $it")
            if (binding?.mainWebView != null) {
                binding?.mainWebView?.loadUrl("https://www.google.com/search?q=$it")
            } else {
                Log.e("Toru", "MainWebViewFragment!! WebView is null!")
            }
        })
    }

    override fun initDependencyInjection() {}

    companion object {
        fun getInstance() = MainWebViewFragment()
    }
}
