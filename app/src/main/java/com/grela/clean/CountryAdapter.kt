package com.grela.clean

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grela.clean.model.CountryViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.country_row.view.*
import kotlinx.android.synthetic.main.title_row.view.*

class CountryAdapter(
    val itemClickListener: (CountryViewModel) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TITLE_TYPE = 0
        private const val ITEM_TYPE = 1
    }

    init {
        setHasStableIds(true)
    }

    private var list = mutableListOf<CountryViewModel>()

    fun updateData(list: List<CountryViewModel>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(container.context)
        return when (viewType) {
            TITLE_TYPE -> createTitleViewHolder(container, inflater)
            ITEM_TYPE -> createCountryViewHolder(container, inflater)
            else -> throw IllegalStateException()
        }
    }

    override fun getItemCount(): Int = list.size + 1

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TITLE_TYPE
            else -> ITEM_TYPE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TitleViewHolder -> holder.bind()
            is CountryViewHolder -> holder.bind(list[position - 1])
        }
    }

    private fun createTitleViewHolder(parent: ViewGroup, inflater: LayoutInflater) = TitleViewHolder(inflater.inflate(R.layout.title_row, parent, false))

    class TitleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind() {
            itemView.title.text = itemView.context.getString(R.string.countries)
        }
    }

    private fun createCountryViewHolder(parent: ViewGroup, inflater: LayoutInflater) = CountryViewHolder(inflater.inflate(R.layout.country_row, parent, false))

    inner class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: CountryViewModel) {
            with(itemView) {
                countryName.text = data.name
                setOnClickListener { itemClickListener(data) }
                Picasso.get().load(data.flag).into(countryFlag)
            }
        }
    }

}