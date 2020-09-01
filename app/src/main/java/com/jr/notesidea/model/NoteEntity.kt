package com.jr.notesidea.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data  class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo(name = "note_name")
    var noteName: String?,
    @ColumnInfo(name = "note_description")
    var noteDescription: String?

)
