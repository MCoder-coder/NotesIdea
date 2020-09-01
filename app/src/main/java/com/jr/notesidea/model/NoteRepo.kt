package com.jr.notesidea.model


import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData


class  NoteRepo(private val noteDao: NoteDao) {

    val allNotes : LiveData<List<NoteEntity>> = noteDao.getAllNotes()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(noteEntity: NoteEntity){
        noteDao.insert(noteEntity)
    }



   fun delteNote(noteEntity: NoteEntity){
       noteDao.delete(noteEntity)
   }

    fun  update(noteEntity: NoteEntity) {
          return  noteDao.update(noteEntity)
    }

    fun change(id: Int, title: String, description : String){
        noteDao.change(id,title, description)
    }

    suspend fun getTask(id : Int): NoteEntity{
        return noteDao.getTask(id)
    }


}