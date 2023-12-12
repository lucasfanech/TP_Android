package fr.unilasalle.fanech.tp_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RoomViewModelFactory(val database: AppDatabase) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RoomViewModel::class.java)) {
            RoomViewModel(database) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}