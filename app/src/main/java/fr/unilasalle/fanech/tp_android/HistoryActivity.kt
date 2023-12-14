package fr.unilasalle.fanech.tp_android

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room

class HistoryActivity : AppCompatActivity(){
    private lateinit var db: AppDatabase
    private lateinit var productViewModel: RoomViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "product-database"
        ).allowMainThreadQueries().build()


        productViewModel = RoomViewModelFactory(db).create(RoomViewModel::class.java)

        // get max cart id and print it
        val maxCartId = productViewModel.getMaxCartId()
        Log.d("maxCartId", maxCartId.toString())



    }

}
