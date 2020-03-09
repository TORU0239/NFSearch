package sg.toru.nfsearch.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initDependencyInjection()
    }

    abstract fun initDependencyInjection()
    abstract fun getLayoutId():Int
}