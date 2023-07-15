package com.example.lerword_mvp.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.lerword_mvp.Model.Word
import com.example.lerword_mvp.R

/*
 * Created by Skyain1 on 26.06.2023.
 */

interface ButtonClickListener{
    fun onButtonRightClick()
    fun onButtonLeftClick()
}

class CardStackAdapter(
    private val context: Context,
    private val words: List<Word>,
    private val itemClickListener: ButtonClickListener,
) : RecyclerView.Adapter<CardStackAdapter.WordViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.card_viewholder, parent, false)
        return WordViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = words[position]
        holder.bind(word)
    }

    override fun getItemCount(): Int {
        return words.size
    }

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

      private  var textView: TextView =itemView.findViewById(R.id.textView)
        private  var  theme: TextView = itemView.findViewById(R.id.textView3)
        private  var perevod: TextView= itemView.findViewById(R.id.perevod)
        private  var newword: TextView  = itemView.findViewById(R.id.textView2)
        private  var  indicator: ImageView = itemView.findViewById(R.id.imageView)
        private  var leftbutton: ConstraintLayout = itemView.findViewById(R.id.leftbutton)
        private  var rightbutton: ConstraintLayout= itemView.findViewById(R.id.rightbutton)
        private  var openbutton: ConstraintLayout= itemView.findViewById(R.id.openbutton)
         var translate: ConstraintLayout= itemView.findViewById(R.id.translate)
         var op: ImageButton = itemView.findViewById(R.id.op)

        fun bind(word: Word) {
            textView.text = word.Title
            theme.text = word.Theme
            perevod.text = word.Translate
            translate.visibility = View.INVISIBLE
            openbutton.visibility = View.VISIBLE
            leftbutton.setBackgroundColor(Color.parseColor("#00000000"))
            rightbutton.setBackgroundColor(Color.parseColor("#00000000"))
            if (word.CheckCount > 0) {
                if (word.know) {
                    indicator.setImageResource(R.drawable.icon_know_word)
                    newword.text = "Повтор заученного слова"
                } else {
                    indicator.setImageResource(R.drawable.icon_exists_word)
                    newword.text = "${word.CheckCount}й повтор слова"
                }
            } else {
                indicator.setImageResource(R.drawable.new_word)
                newword.text = "Новое слово"
            }

            leftbutton.setOnClickListener {
                leftbutton.setBackgroundColor(Color.parseColor("#EEDEA4"))
                itemClickListener.onButtonLeftClick()

            }

            rightbutton.setOnClickListener {
                rightbutton.setBackgroundColor(Color.parseColor("#A4A5EE"))
                itemClickListener.onButtonRightClick()
            }

            op.setOnClickListener {
                openbutton.visibility = View.INVISIBLE
                translate.alpha = 0f
                translate.visibility = View.VISIBLE
                translate.animate()
                    .alpha(1f)
                    .setDuration(300)
                    .start()
            }
        }
    }
}