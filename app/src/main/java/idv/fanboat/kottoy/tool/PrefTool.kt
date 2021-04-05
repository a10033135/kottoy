package idv.fanboat.kottoy.tool

import android.app.Activity
import android.content.Context
import android.util.Log
import me.yokeyword.fragmentation.SupportActivity

object PrefTool {

    val TAG = PrefTool::class.java.simpleName
    val PREF_KEY_USER = "USER"
    val PREF_USER_EMAIL = "USER_EMAIL"

    fun isLogin(activity: Activity): Boolean {
        val email = getUserEmail(activity)
        Log.i(TAG, "isLogin: ${email.isNotEmpty()}")
        return email.isNotEmpty()
    }

    fun getUserEmail(activity: Activity): String {
        val email =
            activity.getSharedPreferences(PREF_KEY_USER, Context.MODE_PRIVATE).getString(PREF_USER_EMAIL, "")
                ?: ""
        return email
    }


}