package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    private var RC_SIGN_IN = 9001
    private var googleSignInClient: GoogleSignInClient? = null
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpBtn.setOnClickListener {
            val signUpIntent = Intent(this, SignUpActivity::class.java)
            startActivity(signUpIntent)
        }

        binding.loginBtn.setOnClickListener {
            val loginIntent = Intent(this, MainActivity::class.java)
            startActivity(loginIntent)
        }

        auth = Firebase.auth

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("645703817309-td4ncutvv6o12giage4fvq1hee6hq57v.apps.googleusercontent.com")
            .requestEmail()
            .build()

        val signInGoogleBtn: SignInButton = binding.googleLoginBtn
        signInGoogleBtn.setOnClickListener{
            googleSignInClient = GoogleSignIn.getClient(this, gso)
            val signInIntent = googleSignInClient!!.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                val account = task.getResult(ApiException::class.java)!!
                account.idToken?.let { firebaseAuthWithGoogle(it) }
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }catch (e: ApiException){
                Toast.makeText(this, "로그인 실패!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) { //성공
                    val user = auth.currentUser
                    user?.let {

                        val name = user.displayName
                        val email = user.email
                        val displayName = user.displayName
                        val photoUrl = user.photoUrl
                        val emailVerified = user.isEmailVerified
                        val uid = user.uid
                        Log.d("xxxx name",name.toString())
                        Log.d("xxxx email",email.toString())
                        Log.d("xxxx displayName",email.toString())

                    }

                } else { //실패
                    Log.d("xxxx ","signInWithCredential:failure", task.exception)
                }
            }
    }

}