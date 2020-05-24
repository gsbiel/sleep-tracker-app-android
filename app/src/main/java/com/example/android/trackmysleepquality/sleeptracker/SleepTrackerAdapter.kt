package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.TextItemViewHolder
import com.example.android.trackmysleepquality.database.SleepNight
import org.w3c.dom.Text

class SleepTrackerAdapter: RecyclerView.Adapter<TextItemViewHolder>() {

    var data = listOf<SleepNight>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    // Essa função é chamada pelo RecyclerView para saber quantos itens a lista tem o no total.
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.sleepQuality.toString()
    }

    // parent é o RecyclerView.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {

        // Estamos criando um contexto de layout para inflar o layout criado em text_list_item.
        // No caso, o contexto é toda a região interna do RecyclerView
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater.inflate(R.layout.text_list_item, parent,false) as TextView

        return TextItemViewHolder(view)
    }

}