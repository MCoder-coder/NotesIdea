package com.jr.notesidea.view.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.jr.notesidea.R

import kotlinx.android.synthetic.main.activity_new_not.*
import kotlinx.android.synthetic.main.activity_note_detalle.*


class NewNoteActivity : AppCompatActivity() {


    private lateinit var editNoteView: EditText
    private lateinit var editDescription: EditText

    // private var editNoteView: TextInputEditText? = null
    //private var editDescription: TextInputEditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_not)



        toolbarMainNewNote.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_arrow_back_24)
        toolbarMainNewNote.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        editDescription = findViewById(R.id.edit_description)
        editNoteView = findViewById(R.id.edit_note)


        val button = findViewById<FloatingActionButton>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(edit_note.text) and TextUtils.isEmpty(editDescription.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)


            } else {
                val note = edit_note.text.toString()
                val notedescription = editDescription.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, note)
                replyIntent.putExtra(EXTRA_DESCRIPTION, notedescription)
                setResult(Activity.RESULT_OK, replyIntent)
                Toast.makeText(this, applicationContext.getString(R.string.toast_add), Toast.LENGTH_SHORT).show()
            }
            finish()
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

        ad_view_newnote1.loadAd(adRequest)
         ad_view_newnote2.loadAd(adRequest)

    }




    companion object {
        const val EXTRA_REPLY = "notes_item"
        const val EXTRA_DESCRIPTION = "notes_description"
        const val EXTRA_ID = "note_id"
    }

    // Called when leaving the activity
    public override fun onPause() {
        ad_view_newnote1.pause()
        ad_view_newnote2.pause()

        super.onPause()
    }

    // Called when returning to the activity
    public override fun onResume() {
        super.onResume()
        ad_view_newnote1.resume()
        ad_view_newnote2.resume()


    }

    // Called before the activity is destroyed
    public override fun onDestroy() {
        ad_view_newnote1.destroy()
        ad_view_newnote2.destroy()

        super.onDestroy()
    }


}







