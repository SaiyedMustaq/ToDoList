package com.mustaq.todolist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
public interface ToDoDao {
    @Query("SELECT * FROM ToDoTable")
    fun getAllToDoList(): LiveData<List<TodoModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertToDo(toDoDataModel: TodoModel)

    @Query("DELETE FROM ToDoTable")
    fun deleteAllToDo()

    @Query("DELETE FROM ToDoTable WHERE taskId=:todoId")
    fun deleteSelectedItem(todoId: Int)


    @Query("UPDATE ToDoTable SET taskTitle=:updatedTitle,taskPriority=:updatedPriority,taskDescription=:updateDescription WHERE taskId = :todoId")
    fun updateToDo(
        todoId: Int,
        updatedTitle: String,
        updatedPriority: String,
        updateDescription: String
    )

    //ascending
    @Query("SELECT * from ToDoTable ORDER BY taskPriority ASC")
    fun getToDoListAscendingOrder(): LiveData<List<TodoModel>>


    @Query("SELECT * from ToDoTable ORDER BY taskPriority DESC")
    fun getToDoListDescendingOrder(): LiveData<List<TodoModel>>


}