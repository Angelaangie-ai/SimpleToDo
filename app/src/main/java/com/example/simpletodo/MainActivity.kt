import com.example.angelaToDo.TaskItemAdapter
import com.example.simpletodo.R

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    var listOfTasks = mutableListOf<String>()
    lateinit var adapter: TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onLongClickListener = object: TaskItemAdapter.OnLongClickListener {
            override fun onItemLongClicked(position: Int) {
                // Removing item from the list
                listOfTasks.removeAt(position)
                // Data set change
                adapter.notifyDataSetChanged()

                saveItems()
            }

        }

        loadItems()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = TaskItemAdapter(listOfTasks, onLongClickListener)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Button and input field for user to add tasks
        val inputTextField = findViewById<EditText>(R.id.addTaskField)

        // Reference to the button and setting onclickListener
        findViewById<Button>(R.id.button).setOnClickListener {

            val userInputtedTask = inputTextField.text.toString()
            listOfTasks.add(userInputtedTask)
            adapter.notifyItemInserted(listOfTasks.size - 1)

            // Reset text field
            inputTextField.setText("")

            saveItems()

        }
    }

    // By writing and reading from a file

    // Get the file
    fun getDataFile() : File {
        return File(filesDir, "data.txt")
    }

    // Load the items by reading the file
    fun loadItems() {
        try {
            listOfTasks = FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }

    // Save items by writing to file
    fun saveItems() {
        try {
            FileUtils.writeLines(getDataFile(), listOfTasks)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }

}
