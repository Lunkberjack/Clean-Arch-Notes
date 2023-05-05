package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes

import androidx.room.Index
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType

/**
 * All of the states that can change in the main screen.
 */
data class NotesState(
    // The list of notes, a column of horizontal divs (so to call them).
    val notes: List<Note> = emptyList(),
    // The default order is by date and descending. It will be implemented
    // with a radioButton group.
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    // The filter/order section will not be visible by default. We can toggle this.
    val isOrderSectionVisible: Boolean = false
)