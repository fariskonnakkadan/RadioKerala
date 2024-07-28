package com.faris.radiokerala

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RadioStationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Full screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide() // Optional: Hide ActionBar if present
        setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val stations = listOf(
            RadioStation("ClubFM", "https://eu10.fastcast4u.com/clubfmuae"),
            RadioStation("Mango", "https://bcovlive-a.akamaihd.net/19b535b7499a4719a5c19e043063f5d9/ap-southeast-1/6034685947001/profile_2/chunklist.m3u8"),
            RadioStation("BBC World Service","https://stream.live.vc.bbcmedia.co.uk/bbc_world_service"),
            RadioStation("NPR","https://npr-ice.streamguys1.com/live.mp3"),
            RadioStation("Calicut","https://air.pc.cdn.bitgravity.com/air/live/pbaudio082/chunklist.m3u8"),
            RadioStation("Tirur","https://sonic01.instainternet.com/8002/stream")


            // Add more stations as needed
        )
        adapter = RadioStationAdapter(stations) { position ->
            togglePlayStop(position, stations[position].streamUrl)
        }
        recyclerView.adapter = adapter
    }

    private fun togglePlayStop(position: Int, streamUrl: String) {
        if (adapter.currentPlayingPosition != null && adapter.currentPlayingPosition != position) {
            // Stop currently playing station if different one is clicked
            stopRadioService()
            adapter.updateCurrentPlayingPosition(null)
        }

        if (adapter.currentPlayingPosition == position) {
            // Toggle stop if same station is clicked
            stopRadioService()
            adapter.updateCurrentPlayingPosition(null)
        } else {
            // Start new station
            startRadioService(streamUrl)
            adapter.updateCurrentPlayingPosition(position)
        }
    }

    private fun startRadioService(streamUrl: String) {
        val intent = Intent(this, RadioService::class.java)
        intent.putExtra("STREAM_URL", streamUrl)
        startService(intent)
    }

    private fun stopRadioService() {
        // Implement logic to stop radio service
        val intent = Intent(this, RadioService::class.java)
        stopService(intent)
    }
}
