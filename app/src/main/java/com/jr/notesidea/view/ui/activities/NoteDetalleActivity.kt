package com.jr.notesidea.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jr.notesidea.R
import com.jr.notesidea.model.NoteEntity
import com.jr.notesidea.model.NoteRepo
import com.jr.notesidea.view.adapter.NoteAdapter

import com.jr.notesidea.viewmodel.NoteViewModel

import kotlinx.android.synthetic.main.activity_edit2.*
import kotlinx.android.synthetic.main.activity_note_detalle.*



@Suppress("NAME_SHADOWING")
class NoteDetalleActivity : AppCompatActivity() {

    var noteViewModel: NoteViewModel? = null

    lateinit var tvdescription: TextView
    private val newNoteActivityRequestCode = 1
    lateinit var NoteRepo: NoteRepo
    val adapter: NoteAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detalle)
//        setSupportActionBar(findViewById(R.id.toolbarDetalle))


        toolbarDetalle.navigationIcon =
            ContextCompat.getDrawable(this, R.drawable.ic_baseline_arrow_back_24)
        toolbarDetalle.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        // cliente = intent.extras.get("cliente") as Cliente
        var intent = intent
        val name = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        //val id = intent.getStringExtra("id")
        val id = intent.getIntExtra("id", 0)
        val idview = findViewById<TextView>(R.id.id_note)
        val resulttv = findViewById<TextView>(R.id.tvTituloNotaActivityDetalle)
        val resttv2 = findViewById<TextView>(R.id.tvDescripcionNotaActivityDetalle)



        resulttv.text = name
        resttv2.text = description
        idview.text = id.toString()

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)


        val btnedit = findViewById<FloatingActionButton>(R.id.btn_editar)


        btnedit.setOnClickListener {
           // val noteEntity: NoteEntity? = null


            val intent = Intent(this, EditActivity::class.java)
            var bundle = Bundle()
            //  bundle.putParcelable("not", noteEntity)
            intent.putExtra("bundle", bundle)
            intent.putExtra("id", id)
            intent.putExtra("title", name)
            intent.putExtra("description", description)
            startActivity(intent)


        }

       // Initialize the Mobile Ads SDK with an AdMob App ID.
        MobileAds.initialize(this) {}

        // Set your test devices. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("ABCDEF012345"))
        // to get test ads on this device."
        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder()
                .setTagForChildDirectedTreatment(TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE)
                .build()
        )

        // Create an ad request.
        val adRequest = AdRequest.Builder().build()

        // Start loading the ad in the background.
        ad_view.loadAd(adRequest)
        ad_view2.loadAd(adRequest)

    }

    // Called when leaving the activity
    public override fun onPause() {
        ad_view.pause()
        ad_view2.pause()

        super.onPause()
    }

    // Called when returning to the activity
    public override fun onResume() {
        super.onResume()
        ad_view.resume()
        ad_view2.resume()

    }

    // Called before the activity is destroyed
    public override fun onDestroy() {
        ad_view.destroy()
        ad_view2.destroy()

        super.onDestroy()
    }

}















