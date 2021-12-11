package ru.dmitry.kuzmenchuk.newstackapp.ui.main

import kotlinx.coroutines.flow.MutableStateFlow

class MainStateHolder : MutableStateFlow<MainState> by MutableStateFlow(MainState.INITIAL)