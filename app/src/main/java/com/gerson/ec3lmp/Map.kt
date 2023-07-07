package com.gerson.ec3lmp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gerson.ec3lmp.databinding.ActivityMainBinding
import com.gerson.ec3lmp.databinding.ActivityMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class Map : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val mapFragment=supportFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        map=p0
        val punto1=LatLng(-4.9038900,-80.6852800)
        val punto2=LatLng(-5.1944900, -80.6328200)
        val punto3=LatLng(-7.1637800, -78.5002700)
        map.addMarker(MarkerOptions().position(punto1).title("Sullana"))
        map.addMarker(MarkerOptions().position(punto2).title("Piura"))
        map.addMarker(MarkerOptions().position(punto3).title("Cajamarca"))
        val bounBuilder=LatLngBounds.builder()
            .include(punto1)
            .include(punto2)
            .include(punto3)
        map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounBuilder.build(),50))

    }
}