package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder

/**
 * We will implement the functionality of these events in the onEvent() method in the
 * ViewModel.
 */
sealed class NotesEvent {
    // It will be sent to the ViewModel when we click on any radio button.
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    // We pass the note we want to delete.
    data class DeleteNote(val note: Note): NotesEvent()
    // We store a note object so we can restore it later (when clicking the Undo button).
    object RestoreNote: NotesEvent()
    // We will toggle the visibility of the section that contains all radio buttons.
    object ToggleOrderSection: NotesEvent()
}
