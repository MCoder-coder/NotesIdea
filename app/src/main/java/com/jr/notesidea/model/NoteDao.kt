package com.jr.notesidea.model

import androidx.lifecycle.LiveData
import androidx.room.*



@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table")
    fun getAll() : List<NoteEntity>

    @Query("SELECT * FROM note_table  ORDER BY  note_name DESC")
    fun getAllNotes() : LiveData<List<NoteEntity>>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insert(noteEntity: NoteEntity)

    @Delete
    fun delete(noteEntity: NoteEntity)

    @Update
    fun update( noteEntity: NoteEntity)

    @Query("UPDATE note_table set id = :id , note_name = :title, note_description = :description")
    fun change(id:Int, title: String, description: String )

    //@Query("SELECT * from note_table WHERE id = :Id")
    //suspend fun getTask(Id: Int):NoteEntity

    @Query("SELECT * from note_table WHERE id = :Id")
    suspend fun getTask(Id: Int):NoteEntity


}