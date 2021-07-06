package ru.ekhalikov.homework2.presentation.moviedetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.ekhalikov.homework2.R
import ru.ekhalikov.homework2.models.Actor

class ActorListAdapter : RecyclerView.Adapter<ActorListAdapter.ActorViewHolder>() {

    private var actors = emptyList<Actor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_holder_actor, parent, false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount(): Int {
        return actors.size;
    }

    fun setData(actors: List<Actor>) {
        this.actors = actors
        notifyDataSetChanged()
    }

    class ActorViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val image = item.findViewById<ImageView>(R.id.image)
        val name = item.findViewById<TextView>(R.id.name)

        fun bind(actor: Actor) {
            Glide
                .with(image.context.applicationContext)
                .load(actor.imageUrl)
                .into(image)
            name.text = actor.name
        }
    }
}