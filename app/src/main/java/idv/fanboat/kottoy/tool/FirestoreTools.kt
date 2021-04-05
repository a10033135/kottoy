package idv.fanboat.kottoy.tool

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object FirestoreTools {
    private val db = Firebase.firestore
    val KEY_USER = "User"
    val KEY_KOTTO = "Kotto"
    val FIELD_EMAIL = "user_email"

    fun getCollection(key: String): CollectionReference {
        return db.collection(key)
    }
}