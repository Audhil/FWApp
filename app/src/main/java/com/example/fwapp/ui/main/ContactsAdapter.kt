package com.example.fwapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.fwapp.BR
import com.example.fwapp.databinding.ListItemBinding
import com.example.fwapp.model.api.UserDetail

class ContactsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    private val listItems by lazy {
        arrayListOf<UserDetail>()
    }

    val listItemsFull by lazy {
        arrayListOf<UserDetail>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ContactsViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder) {
            is ContactsViewHolder ->
                holder.bind(position, listItems[position])

            else ->
                Unit
        }

    fun addItem(items: List<UserDetail>) {
        listItemsFull.apply {
            clear()
            addAll(items)
        }
        listItems.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = arrayListOf<UserDetail>()
                if (constraint == null || constraint.isEmpty())
                    filteredList.addAll(listItemsFull)
                else {
                    val filterPattern = constraint.toString().trim()
                    listItemsFull.forEach {
                        it.name?.let { name ->
                            if (name.contains(filterPattern, true))
                                filteredList.add(it)

                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listItems.apply {
                    clear()
                    addAll(results?.values as List<UserDetail>)
                }
                notifyDataSetChanged()
            }

        }
    }

    //  ViewHolder
    //  list_item.xml
    inner class ContactsViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, userDetail: UserDetail) {
            binding.apply {
                setVariable(BR.user_detail, userDetail)
                executePendingBindings()
            }
        }
    }
}