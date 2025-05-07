package com.example.retrofitapi.recycler

import UniversityModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapi.OnItemClickListener
import com.example.retrofitapi.R
import kotlin.random.Random


class RecyclerAdopter(
    private val dataset: UniversityModel?,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerAdopter.ViewHolder>() {

    val array = arrayOf(
        R.drawable.highschoolone,
        R.drawable.highschooltwo,
        R.drawable.highschoolthree,
        R.drawable.highschoolfour,
        R.drawable.highschoolfive,
        R.drawable.highschoolsix,
        R.drawable.highschoolseven
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycleritems, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val currentItem = dataset?.get(position)
        val randomNumber = Random.nextInt(0, array.size - 1)

        holder.collageName.text = currentItem?.name
        holder.domainsName.text = currentItem?.domains[0]
        holder.imageView.setImageResource(array[randomNumber])
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
//            val intent = Intent(context, ExpandedScreen::class.java)
//            intent.putExtra("name", currentItem?.name)
//            intent.putExtra("domains", currentItem?.domains[0])
//            intent.putExtra("country", currentItem?.country)
//            intent.putExtra("alpha", currentItem?.alpha_two_code)
//            intent.putExtra("webPages", currentItem?.web_pages[0])
//            context.startActivity(intent)
            listener.itemClick(position, context, currentItem)
        }
    }

    override fun getItemCount(): Int = dataset?.size ?: 0

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val collageName: TextView = view.findViewById<TextView>(R.id.collageNameTextView)
        val domainsName: TextView = view.findViewById<TextView>(R.id.domainsTextView)
        val imageView: ImageView = view.findViewById<ImageView>(R.id.highSchoolView)
    }

}