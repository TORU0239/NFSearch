package sg.toru.nfsearch.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import sg.toru.nfsearch.presentation.main.fragment.MainSearchFragment
import sg.toru.nfsearch.presentation.main.fragment.MainWebViewFragment

class MainPagerAdapter(currentActivity: FragmentActivity): FragmentStateAdapter(currentActivity){
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if(position == 0) {
            MainSearchFragment.getInstance()
        } else {
            MainWebViewFragment.getInstance()
        }
    }
}