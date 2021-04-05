package idv.fanboat.kottoy.view

import android.os.Bundle
import idv.fanboat.kottoy.BuildConfig
import idv.fanboat.kottoy.R
import idv.fanboat.kottoy.tool.PrefTool
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

        if (PrefTool.isLogin(this)) {
            if (findFragment(HomeFragment::class.java) == null) {
                loadRootFragment(R.id.fl_container, HomeFragment.newInstance())
            }
        } else {
            if (findFragment(LoginFragment::class.java) == null) {
                loadRootFragment(R.id.fl_container, LoginFragment.newInstance())
            }
        }
    }
}