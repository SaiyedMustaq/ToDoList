package com.mustaq.todolist

interface onToDoClick {
    fun click(todoModel: TodoModel)
    fun deleteSingleNote(taskId: Int)
}