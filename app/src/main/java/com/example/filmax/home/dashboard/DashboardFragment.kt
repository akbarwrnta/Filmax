package com.example.filmax.home.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.filmax.R
import com.example.filmax.model.Film
import com.example.filmax.util.Preference
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.text.NumberFormat
import java.util.*

import kotlin.collections.ArrayList


class DashboardFragment : Fragment() {

    private lateinit var  preference: Preference
    private lateinit var mDatabase : DatabaseReference

    private var dataList = ArrayList<Film>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preference = Preference(activity!!.applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")

        tv_nama.setText(preference.getValues("nama"))
        if (!preference.getValues("saldo").equals("")){
            currency(preference.getValues("saldo")!!.toDouble(), tv_saldo)
        }

        Glide.with(this)
            .load(preference.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profil)
        rv_nowplaying.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_comingsoon.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataList.clear()
                for (getdataSnapshot in dataSnapshot.children){
                    var film = getdataSnapshot.getValue(Film::class.java)
                    dataList.add(film!!)
                }

                rv_nowplaying.adapter = NowPlayingAdapter(dataList){

                }
                rv_comingsoon.adapter = ComingSoonAdapter(dataList){

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, ""+databaseError.message,
                    Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun currency(harga : Double, textview : TextView){
        val localID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localID)
        textview.setText(format.format(harga))
    }
}