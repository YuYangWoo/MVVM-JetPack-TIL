package com.example.dynamicrecyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicrecyclerview.databinding.HolderGroupItemBinding
import com.example.dynamicrecyclerview.databinding.HolderStudentItemBinding

class StudentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var dataList: Array<StudentData>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == GROUP) {
            ItemViewHolder.GroupViewHolder(HolderGroupItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
        else {
            ItemViewHolder.StudentViewHolder(HolderStudentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when(dataList[position].viewType) {
            0 -> GROUP
            else -> ITEM
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("TAG", dataList[position].toString())
        when (dataList[position].viewType) {
            GROUP -> {
                (holder as ItemViewHolder.GroupViewHolder).bind(dataList[position].groupContent as GroupItem)
            }
            else -> {
                (holder as ItemViewHolder.StudentViewHolder).bind(dataList[position].groupContent as StudentItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    companion object {
        const val GROUP = 0
        const val ITEM = 1
    }


}
