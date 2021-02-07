package ru.ekhalikov.homework2.ui.moviedetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.ekhalikov.homework2.R

class ActorAdapter : RecyclerView.Adapter<ActorAdapter.ActorViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_holder_actor, parent, false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 10;
    }

    class ActorViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val image = item.findViewById<ImageView>(R.id.image)
        val name = item.findViewById<TextView>(R.id.name)

        fun bind() {

        }
    }
}