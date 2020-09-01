package com.jr.notesidea.viewmodel

import android.app.Application

import androidx.lifecycle.*
import com.jr.notesidea.model.NoteDao
import com.jr.notesidea.model.NoteRoomDatabase
import com.jr.notesidea.model.NoteEntity
import com.jr.notesidea.model.NoteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel (application: Application) : AndroidViewModel(application) {

    private val NoteRepo : NoteRepo
    private var list: LiveData<List<NoteEntity>>


    init {
        val noteDao = NoteRoomDatabase.getDatabase(application, viewModelScope).noteDao()
        NoteRepo = NoteRepo(noteDao)
        list = noteDao.getAllNotes()
        list = NoteRepo.allNotes

    }

    fun insert(note: NoteEntity) = viewModelScope.launch(Dispatchers.IO){
        NoteRepo.insert(note)
    }
    fun getListNotes() : LiveData<List<NoteEntity>> = list


    fun getUpdate (): LiveData<List<NoteEntity>> = list

    fun delete(note: NoteEntity) = viewModelScope.launch{
        NoteRepo.delteNote(note)
    }

    fun update(note: NoteEntity) = viewModelScope.launch {
        NoteRepo.update(note)
    }



}
