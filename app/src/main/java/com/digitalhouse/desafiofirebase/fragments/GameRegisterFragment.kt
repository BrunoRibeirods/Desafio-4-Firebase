package com.digitalhouse.desafiofirebase.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.digitalhouse.desafiofirebase.databinding.FragmentGameRegisterBinding
import com.digitalhouse.desafiofirebase.models.Game
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class GameRegisterFragment : Fragment() {

    lateinit var database: FirebaseDatabase
    lateinit var reference: DatabaseReference
    private var _binding: FragmentGameRegisterBinding? = null
    private val binding get() = _binding!!
    lateinit var storageReference: StorageReference
    private var imgURl: String? = null
    private var game: Game? = null
    private val CODE_IMG = 1000
    private var ID = 1000



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGameRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        config(ID)
        connectDB()


        binding.btnSelectPic.setOnClickListener {
            setIntent()
        }

        binding.btnSaveGame.setOnClickListener {
            val title = binding.etNameGame.text.toString()
            val date = binding.etDateGame.text.toString()
            val description = binding.etDescriptionGame.text.toString()
             game = getProduct(title, date, description, imgURl)

            game?.let { it1 -> sendProductDB(it1, game!!.title!!) }

            activity?.onBackPressed()
        }

        return view
    }

    fun config(id: Int){
        ID++
        storageReference = FirebaseStorage.getInstance().getReference(id.toString())
    }

    fun setIntent(){
        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Captura IMG"), CODE_IMG)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CODE_IMG){
            val uploadTask = data?.data?.let { storageReference.putFile(it) }
            uploadTask?.continueWithTask {task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Chegando", Toast.LENGTH_SHORT).show()
                }
                storageReference.downloadUrl
            }?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    val url = downloadUri.toString()
                        .substring(0, downloadUri.toString().indexOf("&token"))
                    Log.i("Link Direto ", url)

                    imgURl = url

                    context?.let {
                        Glide.with(it)
                            .load(downloadUri.toString())
                            .into(binding.imagePreview)
                    }
                }
            }
        }
    }

    fun connectDB(){
        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Games")
    }

    fun getProduct(title: String, date: String, description: String, img: String?): Game{

        return Game(title, date, description, img)
    }

    fun sendProductDB(game: Game, title: String): String{
        var res = reference.child(title).setValue(game)
        return res.toString()
    }




}