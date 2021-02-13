package arun.pkg.landrecorder.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "owners")
data class Owners(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String
) {
    constructor(name: String) : this(0, name)
}

@Entity(tableName = "khasras")
data class Khasra(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val khasra: String
) {
    constructor(khasra: String) : this(0, khasra)
}

@Entity(tableName = "record")
data class Record(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val khasra: String,
    val area: Int,
    val partOwned: Double,
    val allotType: String
) {
    constructor(
        name: String,
        khasra: String,
        area: Int,
        partOwned: Double,
        allotType: String
    ) : this(0, name, khasra, area, partOwned, allotType)
}