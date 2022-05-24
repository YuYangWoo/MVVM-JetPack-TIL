package com.example.dynamicrecyclerview

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicrecyclerview.databinding.HolderGroupItemBinding
import com.example.dynamicrecyclerview.databinding.HolderStudentItemBinding

class ItemViewHolder {
    class GroupViewHolder(val binding: HolderGroupItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(groupItem: GroupItem) {
            binding.groupItem = groupItem
        }
    }

    class StudentViewHolder(val binding: HolderStudentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                binding.root.context.startActivity(Intent(binding.root.context, StudentActivity::class.java))
            }
        }
        fun bind(studentItem: StudentItem) {
            binding.studentItem = studentItem
        }
    }

}
