package com.example.recyclerviewexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexample.databinding.WordlistItemBinding
import java.util.LinkedList


class WordListAdapter(wordList: LinkedList<String>) :
    RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private val wordList:LinkedList<String> by lazy { wordList }
    private val layoutInflater: LayoutInflater by lazy { layoutInflater }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): WordViewHolder {
        val itemView = layoutInflater.inflate(R.layout.wordlist_item, parent, false)
        return WordViewHolder(itemView, this)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = wordList[position]
        holder.binding.word.text = current
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    inner class WordViewHolder(itemView: View, private var adapter: WordListAdapter) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val binding:WordlistItemBinding by lazy {
            WordlistItemBinding.inflate(layoutInflater)
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val element = wordList[layoutPosition]
            wordList[layoutPosition] = "Clicked! $element"
            adapter.notifyDataSetChanged()
        }
    }
}


