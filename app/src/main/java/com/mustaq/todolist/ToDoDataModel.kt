/*package com.mustaq.todolist

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ToDoTable")
data class ToDoDataModel(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "priority")
    val priority: String?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "description")
    val description: String?
) : Parcelable {


    constructor(parcel: Parcel) : this(
        id = parcel.readInt(),
        title = parcel.readString(),
        priority = parcel.readString(),
        description = parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(priority)
        parcel.writeString(title)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ToDoDataModel> {
        override fun createFromParcel(parcel: Parcel): ToDoDataModel {
            return ToDoDataModel(parcel)
        }

        override fun newArray(size: Int): Array<ToDoDataModel?> {
            return arrayOfNulls(size)
        }
    }
}*/


/**8-high 12-low -13-medium**/