package idv.fanboat.kottoy.tool

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.SupportFragment

fun SupportFragment.getPref(key: String): SharedPreferences.Editor? {
    return activity?.getSharedPreferences(key, Context.MODE_PRIVATE)?.edit()
}

fun SupportFragment.showToast(msg: String) {
    Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show()
}