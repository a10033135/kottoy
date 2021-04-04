package idv.fanboat.kottoy.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import idv.fanboat.kottoy.R
import kotlinx.android.synthetic.main.fragment_login.*
import me.yokeyword.fragmentation.SupportFragment

class LoginFragment : SupportFragment() {
    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt_login?.setOnClickListener { start(HomeFragment()) }
    }


}