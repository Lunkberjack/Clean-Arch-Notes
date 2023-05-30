package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState

/**
 * By using sealed classes and data classes together, you create a hierarchy of related event types that are known and
 * restricted. This allows you to handle different events in a type-safe manner, as you can define functions or when
 * expressions that explicitly handle each event variant.
 */
sealed class AddEditNoteEvent {
    // Every time we enter a new char this event will be triggered.
    data class EnteredTitle(val value: String): AddEditNoteEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditNoteEvent()
    data class EnteredContent(val value: String): AddEditNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState): AddEditNoteEvent()
    data class ChangeColor(val color: Int): AddEditNoteEvent()

    // When an event represents an action that doesn't require any additional data or parameters,
    // it can be defined as an object instead of a data class.
    // In the context of AddEditNoteEvent, the SaveNote event represents the action of saving a note.
    // It doesn't require any additional data or parameters because the event itself signifies the intention
    // to save the note. Therefore, it can be defined as an object.
    // Using an object in this case provides a concise and clear way to represent the event, without the need
    // for unnecessary data or parameters. It also allows you to easily reference the SaveNote event wherever
    // needed in your code without creating multiple instances of it.
    object SaveNote: AddEditNoteEvent()
}
