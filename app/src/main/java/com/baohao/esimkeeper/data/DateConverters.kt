package com.baohao.esimkeeper.data

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate

class DateConverters {
    @TypeConverter
    fun toLocalDate(value: String?): LocalDate? = value?.let(LocalDate::parse)

    @TypeConverter
    fun fromLocalDate(value: LocalDate?): String? = value?.toString()

    @TypeConverter
    fun toInstant(value: Long?): Instant? = value?.let(Instant::ofEpochMilli)

    @TypeConverter
    fun fromInstant(value: Instant?): Long? = value?.toEpochMilli()
}
