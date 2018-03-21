package com.jgarin.customviewmodeltestapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class NumberAdapter(private val listener: (Int) -> Unit) : RecyclerView.Adapter<NumberAdapter.Holder>() {

	private val items = ArrayList<Int>()

	fun setData(data: Collection<Int>) {
		items.clear()
		items.addAll(data)
		notifyDataSetChanged()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
		return Holder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
	}

	override fun getItemCount(): Int = items.size

	override fun onBindViewHolder(holder: Holder, position: Int) {
		val item = items[position]
		holder.text.text = item.toString()
		holder.itemView.setOnClickListener { listener.invoke(item) }
	}

	class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val text = itemView as TextView
	}
}
