package com.mustaq.todolist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ToDoDao {
    @Query("SELECT * FROM ToDoTable")
    fun getAllToDoList(): LiveData<List<ToDoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertToDo(toDoData: ToDoData)

    @Query("DELETE FROM ToDoTable")
    suspend fun deleteAllToDo()

    @Query("DELETE FROM ToDoTable WHERE id=:todoId")
    fun deleteSelectedItem(todoId: Int)

    @Query("UPDATE ToDoTable SET title=:updatedTitle,description=:updatedDescription WHERE id = :todoId")
    fun updateToDo(todoId: Int, updatedTitle: String, updatedDescription: String)

    //ascending
    @Query("SELECT * from ToDoTable ORDER BY priority ASC")
    fun getToDoListAscendingOrder(): LiveData<List<ToDoData>>


    @Query("SELECT * from ToDoTable ORDER BY priority DESC")
    fun getToDoListDescendingOrder(): LiveData<List<ToDoData>>


}