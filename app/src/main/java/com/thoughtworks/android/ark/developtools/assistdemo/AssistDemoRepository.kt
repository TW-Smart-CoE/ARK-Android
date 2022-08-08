package com.thoughtworks.android.ark.developtools.assistdemo

import javax.inject.Inject

class AssistDemoRepository @Inject constructor() {
    fun loadData(userId: String): String {
        return "TestData: $userId"
    }
}