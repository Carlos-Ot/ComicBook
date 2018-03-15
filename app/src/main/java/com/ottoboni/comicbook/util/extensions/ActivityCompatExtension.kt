package com.ottoboni.comicbook.util.extensions


import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

/**
 * Created by caoj on 03/03/18.
 */

fun AppCompatActivity.replaceFragment(fragment: Fragment, @IdRes containerId: Int) {
    supportFragmentManager.transaction {
        replace(containerId, fragment)
    }

}

private inline fun FragmentManager.transaction(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}