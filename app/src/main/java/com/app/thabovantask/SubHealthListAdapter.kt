package com.app.thabovantask

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.thabovantask.databinding.InflateSubHealthListBinding


class SubHealthListAdapter : RecyclerView.Adapter<SubHealthListAdapter.MyViewHolder>() {

    lateinit var healthSubList: List<HealthList.DataList.Health.AccessibleList>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubHealthListAdapter.MyViewHolder {

//      inflates the layout
        val binding =
            InflateSubHealthListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }


    //    Binds data with the viewHolder
    override fun onBindViewHolder(holder: SubHealthListAdapter.MyViewHolder, position: Int) {
        val healthSubList = healthSubList[position]

        holder.typeText.text = healthSubList.type ?: healthSubList.name


        holder.successText.text = if (healthSubList.success) "TRUE" else "FALSE"
        holder.successText.setTextColor(
            if (healthSubList.success) Color.parseColor("#3b8132") else Color.parseColor(
                "#FF0000"
            )
        )

    }

    //    returns the size of list
    override fun getItemCount(): Int {
        return healthSubList.size
    }

    fun setHealthSubList(accessible: ArrayList<HealthList.DataList.Health.AccessibleList>) {
        this.healthSubList = accessible
        notifyDataSetChanged()

    }

    class MyViewHolder(binding: InflateSubHealthListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var typeText = binding.typeText
        var successText = binding.successText
    }
}