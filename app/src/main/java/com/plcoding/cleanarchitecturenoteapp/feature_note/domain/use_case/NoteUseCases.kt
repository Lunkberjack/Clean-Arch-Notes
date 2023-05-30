package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

/**
 * The use cases take care of the Business Logic and the ViewModel takes the result of these
 * cases and creates a readable state for the View. It is a new approach of the MVVM design pattern.
 */
data class NoteUseCases (
    val getNotes: GetNotesUseCase,
    val deleteNote: DeleteNoteUseCase,
    val addNote: AddNoteUseCase,
    val getNote: GetNoteUseCase
)