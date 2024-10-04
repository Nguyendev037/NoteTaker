package com.example.notetaker.data.models

import androidx.compose.ui.graphics.Color
import com.example.notetaker.ui.theme.*

// Enum class presetening the priority of task
enum class Priority(val color: Color) {
    High(HighPriorityColor),
    Medium(MediumPriorityColor),
    Low(LowPriorityColor),
    None(NonePriorityColor),
}