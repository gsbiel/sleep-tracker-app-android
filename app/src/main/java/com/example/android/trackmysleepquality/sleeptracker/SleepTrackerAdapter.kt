package com.example.android.trackmysleepquality.sleeptracker
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

class SleepTrackerAdapter(val clickListener: SleepNightListener  ) : ListAdapter<SleepNight, SleepTrackerAdapter.ViewHolder>(SleepNightDiffCallback()) {

// Não preciso disso mais, pois estou usando ListAdapter
//    var data = listOf<SleepNight>()
//    set(value) {
//        field = value
//        notifyDataSetChanged()
//    }

// Não preciso disso mais, pois estou usando ListAdapter
// Essa função é chamada pelo RecyclerView para saber quantos itens a lista tem o no total.
//    override fun getItemCount(): Int {
//        return data.size
//    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = data[position]

        // getItem é uma função do ListAdapter que nos permite acessar a lista de dados
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    // parent é o RecyclerView.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: SleepNight, clickListener: SleepNightListener) {
            binding.sleep =  item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class SleepNightDiffCallback: DiffUtil.ItemCallback<SleepNight>() {
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem.nightId == newItem.nightId
    }
    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        // SleepNight é um DataClass, logo, podemos usar o operador de igualdade para saber se dois objetos são iguais
        // Isto é, se ambos possuem propriedades com os mesmos valores.
        return oldItem == newItem
    }
}

class SleepNightListener(val clickListener: (sleepId: Long) -> Unit){
    fun onClick(night: SleepNight){
        return clickListener(night.nightId)
    }
}