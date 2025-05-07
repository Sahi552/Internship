package com.example.uidesign

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView

class CustamisedQuestionsAdaptor(

    private val questionsAndAnswers: List<Pair<String, String>>,
    private val context: Context

) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return questionsAndAnswers.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return 1
    }

    override fun getGroup(groupPosition: Int): Any? {
        return questionsAndAnswers[groupPosition].first
    }

    override fun getChild(groupPosition: Int, childPosition: Int): String {
        return questionsAndAnswers[groupPosition].second
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        val listTitle = getGroup(groupPosition) as String
        val convertView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.frequently_asked_questions_group, parent, false)
        val listTitleTextView: TextView =
            convertView.findViewById(R.id.frequentlyAskedQuestionsQuestionTextView)
        listTitleTextView.text = listTitle

        val view = convertView.findViewById<View>(R.id.view_hidden)
        val iconIndicator = convertView.findViewById<ImageView>(R.id.frequentlyAskedQuestionsQuestionImageView)

        if(isExpanded){
            view.visibility = View.GONE
            iconIndicator.setImageResource(R.drawable.up_arrow_icon)
        }else{
            view.visibility = View.VISIBLE
            iconIndicator.setImageResource(R.drawable.down_arraow_icon )
        }
        return convertView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        val expandedListText = getChild(groupPosition, childPosition)
        val convertView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.frequently_asked_questions_list_items, parent, false)
        val expandedListTextView: TextView =
            convertView.findViewById(R.id.frequentlyAskedQuestionsAnswersTextView)
        expandedListTextView.text = expandedListText

        val view = convertView.findViewById<View>(R.id.view_hidden)
        if (isLastChild){
            view.visibility = View.VISIBLE
        }else{
            view.visibility = View.GONE
        }
        return convertView
    }

    override fun isChildSelectable(
        groupPosition: Int,
        childPosition: Int
    ): Boolean {
        return true
    }
}