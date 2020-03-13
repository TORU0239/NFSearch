package sg.toru.nfsearch.presentation.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import sg.toru.nfsearch.R
import sg.toru.nfsearch.data.entity.SearchResult
import sg.toru.nfsearch.databinding.FragmentImagePopupBinding

class MainPopupFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }


    private lateinit var binding:FragmentImagePopupBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_popup, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.item = imageSearchResult
        binding.executePendingBindings()
    }

//    private val imageUrl:String by lazy {
//        arguments?.getString("image_url") ?: ""
//    }
//
//    private val imageTitle:String by lazy {
//        arguments?.getString("image_title") ?: ""
//    }

    private val imageSearchResult:SearchResult? by lazy {
        (arguments?.getParcelable<SearchResult>("image_result"))
    }

    companion object {
        fun newInstance(result:SearchResult) = MainPopupFragment().apply {
            val bundle = Bundle().apply {
                putParcelable("image_result", result)
            }
            arguments = bundle
        }
    }
}
