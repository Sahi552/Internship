package com.example.uidesign

class QuestionsDataItems() {
    fun getData(): List<Pair<String, String>> {
        val expandableDetailList = mutableListOf<Pair<String, String>>()

        val firstQuestion =
            "What will happen after I pay CreditMantri Rs 69? Is it possible to improve my credit score?"
        val secondQuestion = "Is it possible to improve my credit score?"
        val thirdQuestion =
            "Will a payment default done many years ago impact my credit score even now?"

        val firstAnswer = "A HashMap is a data structure used to store key-value pairs. It’s a fundamental part of the Java and Android development ecosystem. In Android, it’s widely used to efficiently store and retrieve data."
        val secondAnswer = "ArrayMap is a data structure provided by the Android framework that efficiently stores key-value pairs. It’s optimized for situations where you have a relatively small number of elements and performance is critical."
        val thirdAnswer = "SparseArray is a data structure provided by the Android platform that efficiently maps integer keys to values. It’s specifically designed for scenarios where you have a large number of keys but only a small subset of them actually have corresponding values. "


        expandableDetailList.add(Pair(firstQuestion,firstAnswer))
        expandableDetailList.add(Pair(secondQuestion,secondAnswer))
        expandableDetailList.add(Pair(thirdQuestion,thirdAnswer))

        return expandableDetailList
    }
}
