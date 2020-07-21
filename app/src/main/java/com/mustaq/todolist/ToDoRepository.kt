package com.mustaq.todolist

import androidx.lifecycle.LiveData

class ToDoRepository(toDoDao: ToDoDao) {
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val getAllToDoList: LiveData<List<ToDoData>> = toDoDao.getAllToDoList()
    val getAscendingToDoList: LiveData<List<ToDoData>> = toDoDao.getToDoListAscendingOrder()
    val getDescendingToDoList: LiveData<List<ToDoData>> = toDoDao.getToDoListDescendingOrder()

}