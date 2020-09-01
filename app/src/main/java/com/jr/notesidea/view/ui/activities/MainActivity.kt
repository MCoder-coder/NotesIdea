package com.jr.notesidea.view.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

import com.jr.notesidea.R

import com.jr.notesidea.model.NoteEntity
import com.jr.notesidea.view.adapter.NoteAdapter
import com.jr.notesidea.view.adapter.NoteListener
import com.jr.notesidea.viewmodel.NoteViewModel
import com.jr.notesidea.viewmodel.SwipeToDeleteCallback

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NoteListener {


    private lateinit var noteViewModel: NoteViewModel
    private val newNoteActivityRequestCode = 1
    var adapter: NoteAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        val adapter = NoteAdapter(this, context = applicationContext)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(applicationContext)



        noteViewModel = ViewModelProvider(this@MainActivity).get(NoteViewModel::class.java)
        noteViewModel.getListNotes().observe(this, Observer { not ->
            not?.let { adapter.setNotes(it ) }

        })





        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                // noteViewModel.delete(viewHolder.adapterPosition)
                // noteViewModel.delete(viewHolder.adapterPosition)

                val position = viewHolder.adapterPosition
                val note = adapter.getNoteATPosition(position)
                noteViewModel.delete(note)
                //val adapter = recyclerview.adapter as NoteAdapter
                //adapter.delete(viewHolder.adapterPosition)

                // adapter.delete(adapter.getNoteAt(viewHolder.layoutPosition))
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerview)


        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewNoteActivity::class.java)
            startActivityForResult(intent, newNoteActivityRequestCode)
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, intetData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intetData)

        if (requestCode == newNoteActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intetData?.let { data ->

                val note = NoteEntity(
                    0,
                    data.getStringExtra(NewNoteActivity.EXTRA_REPLY),
                    data.getStringExtra(NewNoteActivity.EXTRA_DESCRIPTION)
                )

                //7val note = data.getStringExtra(NewNoteActivity.EXTRA_REPLY)
                //     ?.let { it -> NoteEntity(id = it, noteName = it, noteDescription = it) }


                    noteViewModel.insert(note)



            }


        }


    }

    override fun onNotesClicked(noteEntity: NoteEntity, position: Int) {
        var title = noteEntity.noteName
        val description = noteEntity.noteDescription
       // val id = noteEntity.id

        val intent = Intent(this@MainActivity, NoteDetalleActivity::class.java)
        intent.putExtra("id", noteEntity.id)
        intent.putExtra("title", title)
        intent.putExtra("description", description)
        startActivity(intent)
    }

}













