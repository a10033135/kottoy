package idv.fanboat.kottoy

import android.os.Bundle
import idv.fanboat.kottoy.view.LoginFragment
import me.yokeyword.fragmentation.Fragmentation
import me.yokeyword.fragmentation.SupportActivity

class MainActivity : SupportActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .install()

        if (findFragment(LoginFragment::class.java) == null) {
            loadRootFragment(R.id.fl_container, LoginFragment.newInstance())
        }

    }
}