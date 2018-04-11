package com.ottoboni.comicbook.features.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.SignInButton

import com.ottoboni.comicbook.R

class LoginFragment : Fragment(), LoginView {

    private val REQUEST_CODE_SIGN_IN = 1000

    override lateinit var presenter: LoginPresenter

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }

    val onClickListener = View.OnClickListener {
        val signInIntent = presenter.getSignInIntent()
        startActivityForResult(signInIntent, REQUEST_CODE_SIGN_IN)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView: View = inflater.inflate(R.layout.fragment_login, container, false)

        with(rootView) {
            findViewById<SignInButton>(R.id.sign_in_button).apply {
                setSize(SignInButton.SIZE_STANDARD)
                setOnClickListener(onClickListener)
            }

        }

        return rootView
    }

    override fun onResume() {
        super.onResume()
        presenter.loginUser(requireContext())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_CODE_SIGN_IN -> {
                val signInTask = GoogleSignIn.getSignedInAccountFromIntent(data)
                presenter.handleSignInResult(signInTask)
            }
        }
    }
}
