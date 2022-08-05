package com.thoughtworks.android.ark.ui.assistdemo

import javax.inject.Inject

class AssistDemoRepository @Inject constructor() {
    fun loadData(userId: String): String {
        return "TestData: $userId"
    }
}