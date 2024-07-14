package com.faris.radiokerala

data class RadioStation(
    val name: String,
    var streamUrl: String,
    var isPlaying: Boolean = false
) {
    fun togglePlaying() {
        isPlaying = !isPlaying
    }
}
