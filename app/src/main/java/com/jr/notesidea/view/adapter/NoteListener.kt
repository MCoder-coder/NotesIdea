package com.jr.notesidea.view.adapter
import com.jr.notesidea.model.NoteEntity

interface NoteListener {

    fun onNotesClicked(noteEntity: NoteEntity, position : Int)
}