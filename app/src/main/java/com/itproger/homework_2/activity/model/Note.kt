package com.itproger.homework_2.activity.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "Note")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String? = null,
    val desc: String? = null,
    var checkBox: Boolean = false,
) : Serializable
