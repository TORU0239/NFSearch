package sg.toru.nfsearch.presentation.main.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import sg.toru.nfsearch.R
import sg.toru.nfsearch.databinding.FragmentMainWebViewBinding
import sg.toru.nfsearch.presentation.main.base.BaseFragment
import sg.toru.nfsearch.presentation.main.activity.MainActivity


class MainWebViewFragment : BaseFragment() {
    private lateinit var binding:FragmentMainWebViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_web_view, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainWebView.settings.javaScriptEnabled = true
        binding.mainWebView.isNestedScrollingEnabled = true


        (requireActivity() as MainActivity).imageQueryLiveData.observe(viewLifecycleOwner, Observer {
            binding.url = it
        })
    }

    override fun initDependencyInjection() {}

    companion object {
        fun getInstance() =
            MainWebViewFragment()
    }
}
