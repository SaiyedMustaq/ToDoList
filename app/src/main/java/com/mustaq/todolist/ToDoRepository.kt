package com.mustaq.todolist

import androidx.lifecycle.LiveData

class ToDoRepository(val toDoDao: ToDoDao) {
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val getAllToDoListModel: LiveData<List<TodoModel>> = toDoDao.getAllToDoList()
    val getAscendingToDoListModel: LiveData<List<TodoModel>> =
        toDoDao.getToDoListAscendingOrder()
    val getDescendingToDoListModel: LiveData<List<TodoModel>> =
        toDoDao.getToDoListDescendingOrder()

    suspend fun insertTodo(toDoDataModel: TodoModel) {
        toDoDao.insertToDo(toDoDataModel)
    }


    fun deleteAllNotes() {
        toDoDao.deleteAllToDo()
    }

    fun ascendingOrder() {
        toDoDao.getToDoListAscendingOrder()
    }

    fun descendingOrder() {
        toDoDao.getToDoListDescendingOrder()
    }
}