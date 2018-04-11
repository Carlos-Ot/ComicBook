package com.ottoboni.comicbook.features.login

import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.ottoboni.comicbook.features.common.BasePresenter


class LoginPresenter(private val view: LoginView): BasePresenter {

    private lateinit var googleSignInClient: GoogleSignInClient

    init {
        view.presenter = this
    }

    fun loginUser(context: Context) {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)

        val account = GoogleSignIn.getLastSignedInAccount(context)

        if (account !== null) {
            //User already signed in
            Log.d("xablau", "User Already signed in: ${account.displayName}")
        } else {
            //User not signed in, login user instead
            Log.d("xablau", "User not signed in")
        }
    }

    fun handleSignInResult(signInTask: Task<GoogleSignInAccount>) {

        try {
            val account = signInTask.getResult(ApiException::class.java)

            //User successfully signedIn
            Log.d("xablau", "User login successfully: ${account.displayName}")
        } catch (exception: ApiException) {
            //Error
            Log.e("xablau", "signInResult: failed code= ${exception.statusCode}")
        }

    }

    fun getSignInIntent(): Intent = googleSignInClient.signInIntent

    override fun subscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}