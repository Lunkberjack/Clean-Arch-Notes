package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.NoteUseCases
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Directly coupled to the UI. Takes the result of the use cases and puts all of that
 * into a relevant state for the view.
 */

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel() {
    // The state that the View will be listening for. Initialized to empty by default.
    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentlyDeletedNote: Note? = null

    // Depending on the type of event, one action will be performed.
    // These events come from the wrapper class NotesEvent.
    fun onEvent(event: NotesEvent) {
        when(event) {
            /**
             * - If the order and order type are the same as the ones passed by the event, we do nothing.
             * - If they are different, we call a function that retrieves the notes but in the desired order.
             */
            is NotesEvent.Order -> {
                /**
                 * We compare the classes because in the NoteOrder class the Title, Color and Date classes
                 * are normal classes. If we compared them without the ::class, they would just be compared
                 * by referential equality (which is always false since we are comparing two different objects).
                 * This would not happen if they were data classes, but making them data classes would
                 * introduce a lot of problems.
                 */
                if(state.value.noteOrder::class == event.noteOrder::class &&
                    // We compare the order type (ascending or descending). We don't have to compare by classes
                    // because it's an object and not a class.
                    state.value.noteOrder.orderType == event.noteOrder.orderType) {
                    return
                }
                // If we don't return:
                getNotes()
            }
            is NotesEvent.DeleteNote -> {
                // We launch a coroutine.
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    // We restore the note previously deleted.
                    recentlyDeletedNote = event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    // We restore the recently deleted note object and insert it to the db again.
                    // If it is null –which shouldn't ever happen– it returns at launch.
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    // As the recently deleted note has been restored, it is no longer 'recently
                    // deleted' and thus the object doesn't have to exist anymore.
                    recentlyDeletedNote = null
                }
            }
            is NotesEvent.ToggleOrderSection -> {
                // We update the value of the state. If the order section is visible,
                // it toggles it off, and so.
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        noteUseCases.getNotes(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(notes = notes, noteOrder = noteOrder)
            }.launchIn(viewModelScope)
    }
}