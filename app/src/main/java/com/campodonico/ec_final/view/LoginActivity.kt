package com.campodonico.ec_final.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.campodonico.ec_final.R
import com.campodonico.ec_final.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var googleLauncher: ActivityResultLauncher<Intent>
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            // Usuario ya autenticado, redirigir a la pantalla principal
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        firebaseAuth = Firebase.auth
        googleLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    signInFirebaseWithGoogle(account.idToken)
                }catch (e:Exception){
                    
                }
            }
        }


    }
    private fun signInFirebaseWithGoogle(idToken: String?) {
        val authCredential = GoogleAuthProvider.getCredential(idToken,null)
        firebaseAuth.signInWithCredential(authCredential)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    val user: FirebaseUser = firebaseAuth.currentUser!!

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this,"Ocurrio un Error",Toast.LENGTH_SHORT).show()

                }
            }

    }
    private fun setupViews(){
        binding.tilemail.editText?. addTextChangedListener   {text ->
            binding.btnlogin.isEnabled = validedEmailPass(text.toString(), binding.tilpass.editText?.text.toString())
        }

        binding.tilpass.editText?. addTextChangedListener {text ->
            binding.btnlogin.isEnabled = validedEmailPass(binding.tilemail.editText?.text.toString(), text.toString())
        }

        binding.btnlogin.setOnClickListener {
            /*val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()*/
            signInWithEmailPassword()
        }
        binding.btnGoogle.setOnClickListener {
            signInWithGoogle()
        }
        binding.btnSignUp.setOnClickListener {
            signUpWithEmailPassword()
        }
    }

    private fun signUpWithEmailPassword() {
        val email = binding.tilemail.editText?.text.toString()
        val pass = binding.tilpass.editText?.text.toString()
        if(validedEmailPass(email,pass)){
            firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this){
                    if (it.isSuccessful){
                        Toast.makeText(this,"Usuario Creado Correctamente",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this,"Usuario no puedo ser Creado ",Toast.LENGTH_SHORT).show()
                    }
                }
        }else{
            Toast.makeText(this,"Credenciales no validas",Toast.LENGTH_SHORT).show()
        }
    }

    private fun signInWithEmailPassword() {
        val email = binding.tilemail.editText?.text.toString()
        val pass = binding.tilpass.editText?.text.toString()
        signInFirebaseWithEmail(email,pass)
    }

    private fun signInFirebaseWithEmail(email: String, pass: String) {
        firebaseAuth.signInWithEmailAndPassword(email,pass)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    val user = firebaseAuth.currentUser
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this,"Usuario no se encontro ",Toast.LENGTH_SHORT).show()
                }
            }
    }



    private fun signInWithGoogle() {
        val googleSignOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail().build()
        val client: GoogleSignInClient= GoogleSignIn.getClient(this,googleSignOptions)
        val intent=client.signInIntent
        googleLauncher.launch(intent)
    }

    private fun validedEmailPass(email: String, pass: String): Boolean {
        val ValidateEmail = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val ValidatePass =  pass.length>=6
        return ValidateEmail && ValidatePass
    }
}