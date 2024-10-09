package com.example.notetaker

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// The set-yup for dependencies injection
// HiltAndroidApp will contain all the dependencies
@HiltAndroidApp
class TaskApplication: Application() {
}