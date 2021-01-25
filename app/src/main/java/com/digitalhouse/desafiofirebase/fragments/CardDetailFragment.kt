package com.digitalhouse.desafiofirebase.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.digitalhouse.desafiofirebase.R
import com.digitalhouse.desafiofirebase.databinding.FragmentCardDetailBinding
import com.digitalhouse.desafiofirebase.databinding.FragmentHomeBinding
import com.digitalhouse.desafiofirebase.models.Game
import com.google.firebase.database.*


class CardDetailFragment : Fragment() {

    lateinit var database: FirebaseDatabase
    lateinit var reference: DatabaseReference
    private var _binding: FragmentCardDetailBinding? =  null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCardDetailBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.collapsingLayout.title = ""
        binding.titleGame.text = ""
        binding.dateGame.text = ""
        binding.descriptionGame.text = ""
        //binding.ivGameImg.setImageResource()


        binding.toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

        binding.floatingGameEdit.setOnClickListener {
            findNavController().navigate(R.id.action_cardDetailFragment_to_gameRegisterFragment)
        }

        return view
    }





}