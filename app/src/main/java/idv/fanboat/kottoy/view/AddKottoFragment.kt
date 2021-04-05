package idv.fanboat.kottoy.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import idv.fanboat.kottoy.R
import idv.fanboat.kottoy.model.Kotto
import idv.fanboat.kottoy.tool.FirestoreTools
import idv.fanboat.kottoy.tool.PrefTool
import idv.fanboat.kottoy.tool.showToast
import kotlinx.android.synthetic.main.fragment_add_kotto.*
import me.yokeyword.fragmentation.SupportFragment

class AddKottoFragment : SupportFragment() {

    companion object {
        fun newInstance(kotto: Kotto): AddKottoFragment {
            val fragment = AddKottoFragment()
            fragment.mKotto = kotto
            return fragment
        }
    }

    var mKotto: Kotto? = null

    override
    fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_kotto, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        bt_submit.setOnClickListener {
            val name = et_kotto_name.text.toString()
            val url = et_kotto_url.text.toString()
            val price = et_kotto_price.text.toString()
            val kotto = Kotto(PrefTool.getUserEmail(activity!!), name, url, price)
            FirestoreTools
                .getCollection(FirestoreTools.KEY_KOTTO)
                .add(kotto)
                .addOnSuccessListener {
                    showToast("Success")
                    pop()
                }
                .addOnFailureListener { showToast("Fail") }
        }
    }

    private fun initView() {
        mKotto?.let {
            et_kotto_name.setText(it.name)
            et_kotto_price.setText(it.price)
            et_kotto_url.setText(it.url)
        }
    }

}