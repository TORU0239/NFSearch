package sg.toru.nfsearch.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import sg.toru.nfsearch.R
import sg.toru.nfsearch.data.entity.SearchResult
import sg.toru.nfsearch.databinding.FragmentMainSearchBinding
import sg.toru.nfsearch.presentation.BaseFragment
import java.util.*

class MainSearchFragment : BaseFragment(R.layout.fragment_main_search) {

    private var binding:FragmentMainSearchBinding? = null

    override fun initDependencyInjection() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)

        initView()

        val viewModel = (requireActivity() as MainActivity).mainViewModel
        viewModel.imageQueryLiveData.observe(viewLifecycleOwner, Observer {
            Log.e("Toru", "MainSearchFragment!! $it")
            viewModel.request(it, 1)
        })

        viewModel.successResponse.observe(viewLifecycleOwner, Observer {
            Log.e("Toru", "MainSearchFragment success size:: ${it.size}")
//            (binding?.rcvMainSearch?.adapter as MainSearchAdapter).recyclerviewListItem = it as ArrayList<SearchResult>
            (binding?.rcvMainSearch?.adapter as MainSearchAdapter).updateList(it as ArrayList<SearchResult>)
        })

        viewModel.failedResponse.observe(viewLifecycleOwner, Observer {
            Log.e("Toru", "MainSearchFragment failed message $it")
        })
    }

    private fun initView() {
        val adapter = MainSearchAdapter()
        binding?.rcvMainSearch?.adapter = adapter

    }

    companion object {
        fun getInstance() = MainSearchFragment()
    }
}