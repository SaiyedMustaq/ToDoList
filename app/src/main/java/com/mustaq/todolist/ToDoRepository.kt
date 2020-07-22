package com.mustaq.todolist

import androidx.lifecycle.LiveData

class ToDoRepository(val toDoDao: ToDoDao) {
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val getAllToDoListModel: LiveData<List<ToDoDataModel>> = toDoDao.getAllToDoList()
    val getAscendingToDoListModel: LiveData<List<ToDoDataModel>> =
        toDoDao.getToDoListAscendingOrder()
    val getDescendingToDoListModel: LiveData<List<ToDoDataModel>> =
        toDoDao.getToDoListDescendingOrder()

    suspend fun insertTodo(toDoDataModel: ToDoDataModel) {
        toDoDao.insertToDo(toDoDataModel)
    }
}