package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.viemodel.MainViewModel
import com.google.zxing.integration.android.IntentIntegrator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        binding.callButton.setOnClickListener {
            val intentIntegrator  = IntentIntegrator(this)
            intentIntegrator.setBeepEnabled(true)
            intentIntegrator.setOrientationLocked(true)
            intentIntegrator.setCaptureActivity(Capture::class.java)
            intentIntegrator.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if(intentResult.contents != null) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Result")
            builder.setMessage(intentResult.contents)
            builder.setPositiveButton("OK", DialogInterface.OnClickListener {
                dialogInterface, i -> dialogInterface.dismiss()
            })
            builder.show()
        }
        else {
            Toast.makeText(applicationContext, "OOPS... You did not scan anything", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
