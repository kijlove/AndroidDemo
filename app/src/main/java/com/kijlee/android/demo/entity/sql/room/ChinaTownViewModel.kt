package com.kijlee.android.demo.entity.sql.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.entity.sql.room
 * @ClassName:      ChinaTownViewModel
 * @Author:     kij
 * @Description:  城市数据库用到的viewmodel
 * @Date:    2024/2/15 21:22
 * @Version:    1.0
 */
class ChinaTownViewModel(private val chinaTownRoomDao: ChinaTownRoomDao) : ViewModel() {
    val allItems: LiveData<List<ChinaCityRoom>> = chinaTownRoomDao.getCitys().asLiveData()

    /**
     * Launching a new coroutine to insert an item in a non-blocking way
     */
     fun insertItem(item: ChinaCityRoom) {
        viewModelScope.launch {
            chinaTownRoomDao.insert(item)
        }
    }

}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class ChinaTownViewModelFactory(private val chinaTownRoomDao: ChinaTownRoomDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChinaTownViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChinaTownViewModel(chinaTownRoomDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}