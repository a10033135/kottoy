package idv.fanboat.kottoy.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.socks.library.KLog
import idv.fanboat.kottoy.R
import idv.fanboat.kottoy.model.Kotto
import idv.fanboat.kottoy.tool.FirestoreTools
import idv.fanboat.kottoy.tool.PrefTool
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_home.*
import me.yokeyword.fragmentation.SupportFragment
import androidx.core.util.Consumer as Consumer1

class HomeFragment : SupportFragment() {
    companion object {
        val TAG = HomeFragment::class.java.simpleName
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    var mKottoAdapter: KottoAdapter? = null
    val mKottoListener = Consumer<Kotto> {
        start(AddKottoFragment.newInstance(it))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt_add.setOnClickListener {
            start(AddKottoFragment())
        }
        rv_kotto.layoutManager = LinearLayoutManager(activity)
        if (mKottoAdapter == null) {
            mKottoAdapter = KottoAdapter(arrayListOf(), mKottoListener)
            rv_kotto.adapter = mKottoAdapter
        }
        getData()
    }

    private fun getData() {
        activity?.let { activity ->
            FirestoreTools.getCollection(FirestoreTools.KEY_KOTTO)
                .whereEqualTo(FirestoreTools.FIELD_EMAIL, PrefTool.getUserEmail(activity))
                .get()
                .addOnCompleteListener {
                    val kottos = it.result?.toObjects(Kotto::class.java)
                    mKottoAdapter?.setKottos(kottos ?: arrayListOf())
                }
        }
    }
}