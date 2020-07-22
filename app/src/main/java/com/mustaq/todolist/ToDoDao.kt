package com.mustaq.todolist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
public interface ToDoDao {
    @Query("SELECT * FROM ToDoTable")
    fun getAllToDoList(): LiveData<List<ToDoDataModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertToDo(toDoDataModel: ToDoDataModel)

    @Query("DELETE FROM ToDoTable")
    suspend fun deleteAllToDo()

    @Query("DELETE FROM ToDoTable WHERE id=:todoId")
    fun deleteSelectedItem(todoId: Int)

    @Query("UPDATE ToDoTable SET title=:updatedTitle,description=:updatedDescription WHERE id = :todoId")
    fun updateToDo(todoId: Int, updatedTitle: String, updatedDescription: String)

    //ascending
    @Query("SELECT * from ToDoTable ORDER BY priority ASC")
    fun getToDoListAscendingOrder(): LiveData<List<ToDoDataModel>>


    @Query("SELECT * from ToDoTable ORDER BY priority DESC")
    fun getToDoListDescendingOrder(): LiveData<List<ToDoDataModel>>


}