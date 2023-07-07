package com.gerson.ec3lmp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import com.gerson.ec3lmp.databinding.ActivityCameraBinding
import com.gerson.ec3lmp.databinding.ActivityMainBinding

class Camera : AppCompatActivity() {
    private lateinit var binding: ActivityCameraBinding
    private lateinit var openCameraLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnfoto.setOnClickListener {
            if(permissionValidated()){
                takePhoto()
            }
        }
        openCameraLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
            if(result.resultCode== RESULT_OK){
                val photoBitmap=result.data?.extras?.get("data") as Bitmap
                binding.imgImagen.setImageBitmap(photoBitmap)
            }

        }
    }

    private fun takePhoto() {
        val cameraIntent= Intent()
        cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE)
        openCameraLauncher.launch(cameraIntent)

    }

    private fun permissionValidated(): Boolean {
        val cameraPermission=ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)
        val permissionList: MutableList<String> = mutableListOf()
        if (cameraPermission != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.CAMERA)
        }
        if (permissionList.isNotEmpty()){
            ActivityCompat.requestPermissions(this,permissionList.toTypedArray(),600)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            600 ->{
                if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED){
                    takePhoto()
                }
            }
        }
    }
}