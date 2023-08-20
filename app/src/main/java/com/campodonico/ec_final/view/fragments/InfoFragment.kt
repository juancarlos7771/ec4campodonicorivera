package com.campodonico.ec_final.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.campodonico.ec_final.R
import com.campodonico.ec_final.databinding.FragmentInfoBinding
import com.campodonico.ec_final.view.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = Firebase.auth
        val currentUser = firebaseAuth.currentUser
        binding.txtEmail.text = "Correo: ${currentUser?.email}"

        // Resto de tu código

        // Agregar el código para cerrar sesión aquí
        binding.btnSignOut.setOnClickListener {
            firebaseAuth.signOut()
            navigateToLogin()
            // Agregar código para navegar a la pantalla de inicio de sesión
        }
    }
    private fun navigateToLogin() {
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        requireActivity().finish()
    }
}
