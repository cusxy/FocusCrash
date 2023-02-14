package com.example.myfocus

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val items = listOf(
        Item(type = Item.Type.EDIT_TEXT),
        Item(type = Item.Type.EDIT_TEXT),
        Item(type = Item.Type.EDIT_TEXT),
        Item(type = Item.Type.EDIT_TEXT),
        Item(type = Item.Type.EDIT_TEXT),
        Item(type = Item.Type.EDIT_TEXT),
        Item(type = Item.Type.EDIT_TEXT),
        Item(type = Item.Type.EDIT_TEXT),
        Item(type = Item.Type.EDIT_TEXT),
        Item(type = Item.Type.EDIT_TEXT),
        Item(type = Item.Type.BUTTON),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.recycler_view)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = Adapter().apply {
            submitList(items)
        }
    }

    data class Item(
        val type: Type,
    ) {
        enum class Type {
            EDIT_TEXT,
            BUTTON,
        }
    }

    class Adapter : ListAdapter<Item, Adapter.ViewHolder>(DiffCallback()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.item_layout, parent, false) as ViewGroup
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)
            holder.onBind(item)
        }

        class ViewHolder(view: ViewGroup) : RecyclerView.ViewHolder(view) {

            private val editText = view.findViewById<EditText>(R.id.edit_text).apply {
                inputType = InputType.TYPE_CLASS_TEXT
            }
            private val button = view.findViewById<Button>(R.id.button)

            fun onBind(item: Item) {
                editText.isVisible = false
                button.isVisible = false
                when (item.type) {
                    Item.Type.EDIT_TEXT -> {
                        editText.isVisible = true
                    }
                    Item.Type.BUTTON -> {
                        button.isVisible = true
                    }
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Item>() {

        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean = false

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = false
    }
}