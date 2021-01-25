package com.digitalhouse.desafiofirebase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.digitalhouse.desafiofirebase.R
import com.digitalhouse.desafiofirebase.databinding.FragmentCardDetailBinding
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

        arguments?.getString("title").let {
            binding.titleGame.text = it
            binding.collapsingLayout.title = it
        }
        arguments?.getString("date").let {
            binding.dateGame.text = "Lançamento: " + it
        }
        arguments?.getString("description").let {
            binding.descriptionGame.text = it
        }

        arguments?.getString("img").let {
            Glide.with(view.context).asBitmap()
                .load(it)
                .into(binding.ivGameImg)
        }



        binding.toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

        binding.floatingGameEdit.setOnClickListener {
            findNavController().navigate(R.id.action_cardDetailFragment_to_gameRegisterFragment)
        }

        return view
    }





}