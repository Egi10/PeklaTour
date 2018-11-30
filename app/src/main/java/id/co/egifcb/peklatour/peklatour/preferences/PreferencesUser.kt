package id.co.egifcb.peklatour.peklatour.preferences

import android.content.Context
import android.content.SharedPreferences

class PreferencesUser (context: Context) {
    private var sharedPreferences: SharedPreferences
    private var editor: SharedPreferences.Editor

    private val PREFS_FILENAME = "login"
    private val LOGIN = "isLogin"

    val NO = "no"
    val EMAIL = "email"
    val NAMA = "nama"

    init {
        sharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.apply()
    }

    fun createLogin(no: String?, email: String?, nama: String?) {
        editor.putBoolean(LOGIN, true)
        editor.putString(NO, no)
        editor.putString(EMAIL, email)
        editor.putString(NAMA, nama)
        editor.commit()
    }

    fun isLooggedIn(): Boolean {
        return sharedPreferences.getBoolean(LOGIN, false)
    }

    fun getUserDetail(): HashMap<String?, String?> {
        val user = HashMap<String?, String?>()
        user[NO] = sharedPreferences.getString(NO, "")
        user[EMAIL] = sharedPreferences.getString(EMAIL, "")
        user[NAMA] = sharedPreferences.getString(NAMA, "")
        return user
    }

    fun logoutUser() {
        editor.clear()
        editor.commit()
    }
}