package com.app.thabovantask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.thabovantask.databinding.InflateHealthListBinding

class HealthListAdapter : RecyclerView.Adapter<HealthListAdapter.MyViewHolder>() {

    lateinit var healthList: List<HealthList.DataList.Health>
    lateinit var subHealthListAdapter: SubHealthListAdapter

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HealthListAdapter.MyViewHolder {

//        inflates the layout
        val binding =
            InflateHealthListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    //    Binds data with the viewHolder
    override fun onBindViewHolder(holder: HealthListAdapter.MyViewHolder, position: Int) {
        val healthList = healthList[position]

        holder.healthName.text = healthList.name
        subHealthListAdapter = SubHealthListAdapter()
        holder.accessbleRecyclerView.adapter = subHealthListAdapter
        subHealthListAdapter.setHealthSubList(healthList.accessible)
    }

    //    returns the size of list
    override fun getItemCount(): Int {
        return healthList.size
    }

    fun setHealthList(healthAllList: ArrayList<HealthList.DataList.Health>) {

        this.healthList = healthAllList
        notifyDataSetChanged()
    }

    class MyViewHolder(binding: InflateHealthListBinding) : RecyclerView.ViewHolder(binding.root) {

        var accessbleRecyclerView = binding.rcyList
        var healthName = binding.text


    }

}