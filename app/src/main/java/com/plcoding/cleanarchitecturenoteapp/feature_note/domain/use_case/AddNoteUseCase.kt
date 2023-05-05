package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.InvalidNoteException
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository

/**
 * Make sure we add this to the NoteUseCases wrapper class and to AppModule.
 */
class AddNoteUseCase(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        // The validation business logic belongs to the Use Case, and that's why we'll
        // implement it here.
        if(note.title.isBlank()) {
            // We have created a custom Exception (in the Note class).
            throw InvalidNoteException("The title cannot be empty")
        }
        if(note.content.isBlank()) {
            throw InvalidNoteException("The content cannot be empty")
        }
        // If we reach here, the two previous statements were false.
        repository.insertNote(note)
    }
}