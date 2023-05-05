package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.cleanarchitecturenoteapp.ui.theme.CleanArchitectureNoteAppTheme

// FIGMA DESIGN: https://www.figma.com/community/file/855715967691534013/SpaceX-Crew-Dragon-Flight-Control-UI
// DQ Resources: https://www.woodus.com/den/resources/monster_wiki.php?search=a%&lang=eng
// Foodium: https://github.com/PatilShreyas/Foodium
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureNoteAppTheme {
                
            }
        }
    }
}
