package com.example.myapplication.core.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.core.database.entity.ProfilEntity
import com.example.myapplication.core.database.entity.UserEntity

@Database(
    entities = [ProfilEntity::class, UserEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters()
abstract class AppDatabase : RoomDatabase() {
    abstract fun profilDao(): ProfilDao
    abstract fun userDao(): UserDao
}


@Dao
interface ProfilDao {

    @Query("select * from profils")
    fun getListProfils(): LiveData<List<ProfilEntity>?>

    @Query("select * from profils WHERE id LIKE :idProfil")
    fun getProfil(idProfil: String): LiveData<ProfilEntity?>

}

@Dao
interface UserDao {

    @Query("select * from users")
    fun getListUsers(): LiveData<List<UserEntity>?>

    @Query("select * from users WHERE id LIKE :idUser")
    fun getUser(idUser: String): LiveData<UserEntity?>

}