package com.example.android.trackmysleepquality.sleeptracker
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString

class SleepTrackerAdapter : ListAdapter<SleepNight, SleepTrackerAdapter.ViewHolder>(SleepNightDiffCallback()) {

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
        holder.bind(item)
    }

    // parent é o RecyclerView.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){

        val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
        val quality: TextView = itemView.findViewById(R.id.quality_string)
        val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)

        fun bind(item: SleepNight) {
            val res = itemView.context.resources

            sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
            quality.text = convertNumericQualityToString(item.sleepQuality, res)
            qualityImage.setImageResource(when (item.sleepQuality) {
                0 -> R.drawable.ic_sleep_0
                1 -> R.drawable.ic_sleep_1
                2 -> R.drawable.ic_sleep_2
                3 -> R.drawable.ic_sleep_3
                4 -> R.drawable.ic_sleep_4
                5 -> R.drawable.ic_sleep_5
                else -> R.drawable.ic_sleep_active
            })
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                        .inflate(R.layout.list_item_sleep_night, parent, false)
                return ViewHolder(view)
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