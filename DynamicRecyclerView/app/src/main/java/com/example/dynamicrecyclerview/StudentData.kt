package com.example.dynamicrecyclerview

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

enum class StudentData(val viewType: Int, val groupContent: GroupContent) {
    STD1(StudentAdapter.GROUP, GroupItem(3)),
    STD2(
        StudentAdapter.ITEM,
        StudentItem(3, R.drawable.ic_launcher_background, "손흥민", "1001", "010-1111-1111")
    ),
    STD3(
        StudentAdapter.ITEM,
        StudentItem(3, R.drawable.ic_launcher_background, "아이유", "1004", "010-1111-1111")
    ),
    STD4(StudentAdapter.GROUP, GroupItem(4)),
    STD5(
        StudentAdapter.ITEM,
        StudentItem(4, R.drawable.ic_launcher_background, "유양우", "1001", "010-1111-1111")
    ),
    STD6(
        StudentAdapter.ITEM,
        StudentItem(4, R.drawable.ic_launcher_background, "조정석", "1001", "010-1111-1111")
    )
}
    interface GroupContent {
        val std_year: Int
    }
    data class GroupItem(
        override val std_year: Int
    ) : GroupContent

    @Parcelize
    data class StudentItem(
        override val std_year: Int,
        val std_imgRes: Int,
        val std_name: String,
        val std_id: String,
        val std_phone: String
    ) : GroupContent, Parcelable


