package com.example.loginregisdata.storage

import android.content.Context
import android.content.SharedPreferences

class SharePrefManager (mContext : Context){
    companion object {
        const val SP_USER = "spUser"
        const val SP_SIGNED = "signed"
        const val SP_ID = "spid"
    }

    private var sharedPreferences : SharedPreferences
    private var spEditor : SharedPreferences.Editor

    val spSigned : Boolean? get() = sharedPreferences.getBoolean(SP_SIGNED, false)
    val spid : Int? get() = sharedPreferences.getInt(SP_ID, 1)

    init {
        sharedPreferences = mContext.getSharedPreferences(SP_USER, Context.MODE_PRIVATE)
        spEditor = sharedPreferences.edit()
    }

    fun saveBoolean (keySp : String, valueSp : Boolean?) {
        spEditor.putBoolean(keySp, valueSp!!)
        spEditor.commit()
    }

    fun saveId(keyId : String, valueId : Int?) {
        spEditor.putInt(keyId, valueId!!)
        spEditor.commit()
    }

}