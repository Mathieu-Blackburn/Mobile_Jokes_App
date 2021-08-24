package com.nomdelacompagnie.tp2_mathieu_blackburn_rogers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RvAdapter(var items: List<JokeData>) : RecyclerView.Adapter<RvAdapter.RV_Item_VH>(){

    inner class RV_Item_VH(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RV_Item_VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_jokes, parent, false)

        return RV_Item_VH(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RV_Item_VH, position: Int) {
        holder.itemView.apply{
            findViewById<TextView>(R.id.tvSetup).text = items[position].setup
            findViewById<TextView>(R.id.tvPunchline).text = items[position].punchline
        }
    }
}