package com.jr.notesidea.view.ui.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.*
import com.google.android.gms.ads.RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jr.notesidea.R
import com.jr.notesidea.model.NoteDao
import com.jr.notesidea.model.NoteEntity
import com.jr.notesidea.model.NoteModel
import com.jr.notesidea.model.NoteRepo
import com.jr.notesidea.view.adapter.NoteAdapter
import com.jr.notesidea.view.adapter.NoteListener
import com.jr.notesidea.viewmodel.NoteViewModel

import kotlinx.android.synthetic.main.activity_edit2.*

import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.activity_note_detalle.*


@Suppress("NAME_SHADOWING")
class EditActivity : AppCompatActivity() {


    var noteViewModel: NoteViewModel? = null
    var noteAdapter: NoteAdapter? = null
    private var data = NoteModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit2)


        toolbarMainEditNote.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_arrow_back_24)
        toolbarMainEditNote.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



        val editDescription = findViewById<EditText>(R.id.activityedit_description)
        val editNoteTitle = findViewById<EditText>(R.id.activityedit_note)


        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel?.getListNotes()?.observe(this, Observer { not ->
            not?.let { }

        })
        var intent = intent

        val name = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val id = intent.getIntExtra("id", 0)
        //val id : Int? = null


        editNoteTitle.setText(name)
        editDescription.setText(description)
        val btnsave = findViewById<FloatingActionButton>(R.id.fab_save)



        btnsave.setOnClickListener {
            var title = editNoteTitle.text.toString()
            val descrption = editDescription.text.toString()


            if (title.isNotEmpty().and(descrption.isNotEmpty())) {
                val note = NoteEntity(id, noteName = title, noteDescription = descrption)
                Log.i("TAG", "$note")



                    noteViewModel?.update(note)


                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

                Log.i("TAG", "${noteViewModel?.update(note)}")

                Toast.makeText(this,  applicationContext.getString(R.string.toast_update), Toast.LENGTH_SHORT).show()
            }


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
        ad_view_edit1.loadAd(adRequest)
        ad_view_edit2.loadAd(adRequest)

    }

    // Called when leaving the activity
    public override fun onPause() {
        ad_view_edit1.pause()
        ad_view_edit2.pause()

        super.onPause()
    }

    // Called when returning to the activity
    public override fun onResume() {
        super.onResume()
        ad_view_edit1.resume()
        ad_view_edit2.resume()

    }

    // Called before the activity is destroyed
    public override fun onDestroy() {
        ad_view_edit1.destroy()
        ad_view_edit2.destroy()

        super.onDestroy()
    }
}

























