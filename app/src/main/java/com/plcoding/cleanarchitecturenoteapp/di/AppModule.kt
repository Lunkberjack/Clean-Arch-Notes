package com.plcoding.cleanarchitecturenoteapp.di

import android.app.Application
import androidx.room.Room
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.data_source.NoteDatabase
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.NoteRepositoryImpl
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.AddNoteUseCase
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.DeleteNoteUseCase
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.GetNotesUseCase
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dependency injection with Dagger Hilt. Automatically provides dependencies
* where needed. (We need to create a NoteApp class in the root package that
* extends from Application, and modify the manifest with the App's name).
*/

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        // We return a room database of the class NoteDatabase.
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            // The companion object of the NoteDatabase.
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    // It is not necessary to retrieve the DAO with DI, as we can get it
    // directly from the database.
    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    // We can access these two use cases and inject them in the ViewModels thanks to
    // dependency injection.
    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            addNote = AddNoteUseCase(repository)
        )
    }
}