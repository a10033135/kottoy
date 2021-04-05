package idv.fanboat.kottoy.model

import com.google.firebase.firestore.PropertyName

data class Kotto(
    @PropertyName("user_email") var user_email: String = "",
    @PropertyName("name") var name: String = "",
    @PropertyName("url") var url: String = "",
    @PropertyName("price") var price: String = ""
)