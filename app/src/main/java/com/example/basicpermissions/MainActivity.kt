package com.example.basicpermissions


import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import android.Manifest
import android.content.Intent
import android.util.Log

class MainActivity : AppCompatActivity() {

    companion object{
        const val PERMISSION_REQUEST_CAMERA = 0
    }
    private val btnCamera: Button by lazy { findViewById(R.id.btnOpenCamera) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnCamera.setOnClickListener {
            if (checkSelfPermissionCompat(Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED) {
                // Permission is already available, start camera preview
                Toast.makeText(this,"Разрешение  предоставлено", Toast.LENGTH_LONG).show()
                startCamera()
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                Intent(intent)
                startActivity(intent)
            } else {
                // Permission is missing and must be requested.
                requestCameraPermission()
            }

        }
    }




    private fun requestCameraPermission(){
        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
            Toast.makeText(this,"Разрешение не предоставлено", Toast.LENGTH_LONG).show()
            requestPermissionsCompat(arrayOf(Manifest.permission.CAMERA), PERMISSION_REQUEST_CAMERA)
        }else{
            Toast.makeText(this,"Разрешение не может быть запрошено", Toast.LENGTH_LONG).show()
            requestPermissionsCompat(arrayOf(Manifest.permission.CAMERA), PERMISSION_REQUEST_CAMERA)
        }
    }





    private fun startCamera(){
        Log.d("Debug","CAMERA OPENED")
    }





    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Разрешение  предоставлено", Toast.LENGTH_LONG).show()
                startCamera()
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                Intent(intent)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Разрешение не предоставлено", Toast.LENGTH_LONG).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}


fun AppCompatActivity.checkSelfPermissionCompat(permission: String) =
    ActivityCompat.checkSelfPermission(this, permission)

fun AppCompatActivity.shouldShowRequestPermissionRationaleCompat(permission: String) =
    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)

fun AppCompatActivity.requestPermissionsCompat(permissionsArray: Array<String>,
                                               requestCode: Int) {
    ActivityCompat.requestPermissions(this, permissionsArray, requestCode)
}