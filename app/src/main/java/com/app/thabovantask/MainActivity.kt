package com.app.thabovantask

import android.R
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.thabovantask.apiCall.MainRepository
import com.app.thabovantask.apiCall.RetrofitService
import com.app.thabovantask.databinding.ActivityMainBinding
import com.app.thabovantask.viewModel.HealthViewModel
import com.app.thabovantask.viewModel.HealthViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var healthViewModel: HealthViewModel
    lateinit var healthListAdapter: HealthListAdapter
    var healthAllList = ArrayList<HealthList.DataList.Health>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this

//        Calls the network
        val retrofitService = RetrofitService.getInstance()

//        initializes the viewModel
        healthViewModel = ViewModelProvider(
            this,
            HealthViewModelFactory(MainRepository(retrofitService))
        )[HealthViewModel::class.java]

//        Observe the data from mutable livedata
        healthViewModel.getHealthList()
        healthViewModel.healthList.observe(this) { healthList ->

            healthAllList.addAll(healthList)

//            sets the value in adapter class
            healthListAdapter = HealthListAdapter()
            binding.progressBar.visibility = View.GONE
            binding.rcyList.adapter = healthListAdapter
            healthListAdapter.setHealthList(healthAllList)
        }


        binding.viewModel = this
    }

    override fun onBackPressed() {
//        Action for back click
        AlertDialog.Builder(this).setIcon(R.drawable.ic_dialog_alert).setTitle("Exit")
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog, which -> finish() })
            .setNegativeButton("No", null).show()
    }


}