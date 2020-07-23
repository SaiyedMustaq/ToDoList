package com.mustaq.todolist;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "ToDoTable")
public class TodoModel implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    int taskId;
    String taskTitle;
    int taskPriority;
    String taskDescription;


    public TodoModel(String taskTitle, int taskPriority, String taskDescription) {
        this.taskTitle = taskTitle;
        this.taskPriority = taskPriority;
        this.taskDescription = taskDescription;
    }

    protected TodoModel(Parcel in) {
        taskId = in.readInt();
        taskTitle = in.readString();
        taskPriority = in.readInt();
        taskDescription = in.readString();
    }

    public static final Creator<TodoModel> CREATOR = new Creator<TodoModel>() {
        @Override
        public TodoModel createFromParcel(Parcel in) {
            return new TodoModel(in);
        }

        @Override
        public TodoModel[] newArray(int size) {
            return new TodoModel[size];
        }
    };

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public int getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(int taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(taskId);
        dest.writeString(taskTitle);
        dest.writeInt(taskPriority);
        dest.writeString(taskDescription);
    }
}
