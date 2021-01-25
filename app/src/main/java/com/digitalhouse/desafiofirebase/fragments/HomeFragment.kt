package com.digitalhouse.desafiofirebase.fragments

import android.os.Bundle
import android.os.Handler
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.digitalhouse.desafiofirebase.R
import com.digitalhouse.desafiofirebase.adapters.HomeAdapter
import com.digitalhouse.desafiofirebase.databinding.FragmentHomeBinding
import com.digitalhouse.desafiofirebase.models.Game
import com.google.firebase.database.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class HomeFragment : Fragment() {

    lateinit var database: FirebaseDatabase
    lateinit var reference: DatabaseReference
    private var _binding: FragmentHomeBinding? =  null
    private val binding get() = _binding!!
    lateinit var listOfGames: MutableList<Game>




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        connectDB()
        readData()

        binding.btnRegisterGames.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_gameRegisterFragment)
        }

        binding.refreshLayout.setOnRefreshListener {
           readData()
            Handler().postDelayed({}, 1000)
        }





        return view
    }

    fun connectDB(){
        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Games")
    }

    fun readData(){
        listOfGames = mutableListOf()
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                listOfGames = mutableListOf()
                if (dataSnapshot.exists()) {
                    dataSnapshot.children.forEach {
                        val game = it.getValue(Game::class.java)
                        Log.i("game", game.toString())
                        if (game != null) {
                            listOfGames.add(game)

                        }

                    }

                }
                binding.rcGamesHome.apply {
                    adapter = HomeAdapter(listOfGames)
                    layoutManager = GridLayoutManager(context, 2)
                    setHasFixedSize(true)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.i("error ", error.toString())
            }
        })


    }


}