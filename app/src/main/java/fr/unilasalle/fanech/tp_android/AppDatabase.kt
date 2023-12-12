package fr.unilasalle.fanech.tp_android

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

     abstract fun productDao(): ProductDataAccessObject
}
