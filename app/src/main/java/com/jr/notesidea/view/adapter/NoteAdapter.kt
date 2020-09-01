package com.jr.notesidea.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.jr.notesidea.R
import com.jr.notesidea.model.NoteDao
import com.jr.notesidea.model.NoteEntity
import com.jr.notesidea.model.NoteRepo
import com.jr.notesidea.model.NoteRoomDatabase
import java.util.*
import kotlin.collections.ArrayList


class NoteAdapter(val NoteListenr: NoteListener, context: Context) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var nots = emptyList<NoteEntity>()
    private var not = MutableLiveData<NoteEntity>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_notes, parent, false
        )
    )


    override fun getItemCount() = nots.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var current = nots[position]

        holder.tvTitle.text = current.noteName
        holder.tvDescription.text = current.noteDescription
        holder.itemView.setOnClickListener {
            NoteListenr.onNotesClicked(current, position)

        }


    }

    fun getNoteATPosition(position: Int): NoteEntity {

        return nots[position]

    }




    internal fun setNotes(notes: List<NoteEntity> ) {
        this.nots = notes.toMutableList()
        notifyDataSetChanged()

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvTitle = itemView.findViewById<TextView>(R.id.tvTituloNota)
        var tvDescription = itemView.findViewById<TextView>(R.id.tvDescripcionNota)
        var tvid = itemView.findViewById<TextView>(R.id.id_notesholder)


    }
}