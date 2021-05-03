package com.learn.growagain.book.coroutine.starwar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learn.growagain.R

class CharacterListFragment : Fragment(), CharacterListViewRenderer {

    private lateinit var recyclerCharacterList: RecyclerView
    private lateinit var characterListAdapter: CharacterListAdapter
    private lateinit var loadingView: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerCharacterList = view.findViewById(R.id.recycler_character_list)
        recyclerCharacterList.layoutManager = LinearLayoutManager(view.context)
        loadingView = view.findViewById(R.id.loading_character_list)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        characterListAdapter = CharacterListAdapter()
        recyclerCharacterList.adapter = characterListAdapter
    }

    override fun showAllCharacters(characterList: List<Character>) {
        characterListAdapter.updateCharacterList(characterList)
    }

    override fun showLoading() {
        loadingView.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingView.visibility = View.GONE
    }
}

class CharacterListAdapter : RecyclerView.Adapter<CharacterViewHolder>() {

    private val characterList: MutableList<Character> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemVIew =
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(itemVIew)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.tvCharacterName.text = characterList[position].name
    }

    fun updateCharacterList(characterList: List<Character>) = with(this.characterList) {
        clear()
        addAll(characterList)
        notifyDataSetChanged()
    }
}


class CharacterViewHolder(itemVIew: View) : RecyclerView.ViewHolder(itemVIew) {

    val tvCharacterName: TextView = itemVIew.findViewById(R.id.tv_character_name)
}