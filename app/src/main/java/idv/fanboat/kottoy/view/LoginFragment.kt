package idv.fanboat.kottoy.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import idv.fanboat.kottoy.R
import idv.fanboat.kottoy.model.User
import idv.fanboat.kottoy.tool.FirestoreTools
import idv.fanboat.kottoy.tool.FirestoreTools.KEY_USER
import idv.fanboat.kottoy.tool.PrefTool
import idv.fanboat.kottoy.tool.getPref
import idv.fanboat.kottoy.tool.showToast
import kotlinx.android.synthetic.main.fragment_login.*
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportFragment

class LoginFragment : SupportFragment() {
    companion object {
        const val REQUEST_SIGN_IN = 101
        val TAG = LoginFragment::class.java.simpleName
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
        bt_login?.setOnClickListener { signUpGoogleAuth() }
    }

    fun signUpGoogleAuth() {
        activity?.let { activity ->
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
            val googleSignInClient = GoogleSignIn.getClient(activity, gso)
            googleSignInClient.signOut()
            startActivityForResult(googleSignInClient.signInIntent, REQUEST_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == ISupportFragment.RESULT_OK) {
            when (requestCode) {
                REQUEST_SIGN_IN -> {
                    try {
                        GoogleSignIn.getSignedInAccountFromIntent(data)?.result?.let { account ->
                            Log.e(TAG, account.email.toString())
                            FirestoreTools.getCollection(KEY_USER)
                                .add(User(account.id ?: "", account.email ?: ""))
                                .addOnSuccessListener {
                                    showToast("登入成功")
                                    getPref(PrefTool.PREF_KEY_USER)?.putString(
                                        PrefTool.PREF_USER_EMAIL,
                                        account.email
                                    )?.apply()
                                    start(HomeFragment())
                                }
                                .addOnFailureListener {
                                    Log.e(TAG, it.message ?: "")
                                }
                        }
                    } catch (e: ApiException) {
                        e.message?.let { Log.e(TAG, it) }
                    }

                }
            }
        }
    }

}