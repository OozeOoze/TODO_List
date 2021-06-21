package com.ooze.todolist

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing the array lists and the adapter
        val items = arrayListOf<String>()
        val adapter =ArrayAdapter(this,
            android.R.layout.simple_list_item_multiple_choice
            , items)
        // Adding the items to the list when the add button is pressed
        findViewById<Button>(R.id.add).setOnClickListener {

            items.add(findViewById<EditText>(R.id.editText).text.toString())
            findViewById<ListView>(R.id.listView).adapter =  adapter
            adapter.notifyDataSetChanged()
            // This is because every time when you add the item the input space or the edit text space will be cleared
            findViewById<EditText>(R.id.editText).setText("")
        }
        // Clearing all the items in the list when the clear button is pressed
        findViewById<Button>(R.id.clear).setOnClickListener {

            items.clear()
            adapter.notifyDataSetChanged()
        }
        // Adding the toast message to the list when an item on the list is pressed
        findViewById<ListView>(R.id.listView).setOnItemClickListener {
                adapterView, view, i, l -> android.widget.Toast.makeText(
            this, "You Selected the item --> "+ items[i], android.widget.Toast.LENGTH_SHORT).show()
        }
        // Selecting and Deleting the items from the list when the delete button is pressed
        findViewById<Button>(R.id.delete).setOnClickListener {
            val position: SparseBooleanArray = findViewById<ListView>(R.id.listView).checkedItemPositions
            val count = findViewById<ListView>(R.id.listView).count
            var item = count - 1
            while (item >= 0) {                if (position.get(item))
            {
                adapter.remove(items[item])
            }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }
    }



}