package id.co.egifcb.peklatour.peklatour.data.source.local

import android.content.Context
import android.content.SharedPreferences

class PreferencesUser(context: Context) {
    private var sharedPreferences: SharedPreferences
    private var editor: SharedPreferences.Editor

    init {
        sharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.apply()
    }

    fun createLogin(no: String?, email: String?, name: String?) {
        editor.putBoolean(LOGIN, true)
        editor.putString(NO, no)
        editor.putString(EMAIL, email)
        editor.putString(NAME, name)
        editor.commit()
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(LOGIN, false)
    }

    fun getUserDetail(): HashMap<String?, String?> {
        val user = HashMap<String?, String?>()
        user[NO] = sharedPreferences.getString(NO, "")
        user[EMAIL] = sharedPreferences.getString(EMAIL, "")
        user[NAME] = sharedPreferences.getString(NAME, "")
        return user
    }

    fun logoutUser() {
        editor.clear()
        editor.commit()
    }

    companion object {
        private const val PREFS_FILENAME = "login"
        private const val LOGIN = "isLogin"

        const val NO = "no"
        const val EMAIL = "email"
        const val NAME = "nama"
    }
}