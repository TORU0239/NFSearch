package sg.toru.nfsearch.presentation.main.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import sg.toru.nfsearch.R
import sg.toru.nfsearch.app.NFApp
import sg.toru.nfsearch.databinding.FragmentMainSearchBinding
import sg.toru.nfsearch.domain.di.MainDomainModule
import sg.toru.nfsearch.domain.viewmodel.MainViewModel
import sg.toru.nfsearch.presentation.main.base.BaseFragment
import sg.toru.nfsearch.presentation.adapter.MainSearchAdapter
import sg.toru.nfsearch.presentation.main.InfiniteScrollListener
import sg.toru.nfsearch.presentation.main.OnLoadMoreListener
import sg.toru.nfsearch.presentation.main.activity.MainActivity
import javax.inject.Inject

class MainSearchFragment : BaseFragment() {

    private lateinit var binding: FragmentMainSearchBinding

    @Inject
    lateinit var mainViewModel: MainViewModel

    private val scrollListener: InfiniteScrollListener by lazy {
        InfiniteScrollListener(
            (binding.rcvMainSearch.layoutManager as GridLayoutManager),
            loadMoreListener
        )
    }
    private val loadMoreListener: OnLoadMoreListener by lazy {
        object : OnLoadMoreListener {
            override fun onLoadMore() {
                Log.e("Toru", "Added!!!")
                mainViewModel.request(currentQuery, ++mainViewModel.currentPage)
            }
        }
    }

    override fun initDependencyInjection() {
        (requireActivity().application as NFApp).appComponent()
            .mainDomainComponent(MainDomainModule())
            .injectTo(this)
    }

    private var currentQuery:String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_search, container, false)
        (requireActivity() as MainActivity).imageQueryLiveData.observe(viewLifecycleOwner, Observer { query ->
            Log.e("Toru", "MainSearchFragment!! $query")
            currentQuery = query
            mainViewModel.request(query, 1)
        })
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.rcvMainSearch.adapter =
            MainSearchAdapter()
        binding.rcvMainSearch.addOnScrollListener(scrollListener)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.successResponse.observe(viewLifecycleOwner, Observer {
            Log.e("Toru", "MainSearchFragment success size:: ${it.size}")
            scrollListener.setLoaded()
        })
        mainViewModel.failedResponse.observe(viewLifecycleOwner, Observer {
            Log.e("Toru", "MainSearchFragment failed message $it")
        })
    }

    companion object {
        fun getInstance() =
            MainSearchFragment()
    }
}