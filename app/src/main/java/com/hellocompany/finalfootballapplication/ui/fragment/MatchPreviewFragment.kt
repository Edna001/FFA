package com.hellocompany.finalfootballapplication.ui.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hellocompany.finalfootballapplication.R
import com.hellocompany.finalfootballapplication.databinding.MatchPreviewFragmentBinding
import com.hellocompany.finalfootballapplication.service.model.response.MatchResponse
import com.hellocompany.finalfootballapplication.ui.adapter.MatchAdapter
import com.hellocompany.finalfootballapplication.viewmodel.MatchPreviewViewModel
import kotlinx.android.synthetic.main.match_progress_view.*
import kotlinx.android.synthetic.main.match_teams_tile_view.*
import kotlinx.android.synthetic.main.recycler_view.*


class MatchPreviewFragment : Fragment(R.layout.match_preview_fragment) {

    private lateinit var viewModel: MatchPreviewViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val binding: MatchPreviewFragmentBinding = DataBindingUtil.setContentView(this.requireActivity(), R.layout.match_preview_fragment)
        viewModel = ViewModelProviders.of(this).get(MatchPreviewViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.getApiResponse().observeForever {
            createMatch(it)
        }
    }

    private fun createMatch(matchResponse: MatchResponse?){
        initUI(matchResponse)
        getProgress(matchResponse)
        initAdapter(matchResponse)
    }
//    Creating UI
    private fun initUI(matchResponse: MatchResponse?){
    teamTwoId.teamName = matchResponse!!.match.team1!!.teamName
}

//    Loading Progress Bar
    private fun getProgress(matchResponse: MatchResponse?){
        val teamOnePoss: Int = matchResponse!!.match.team1!!.ballPosition!!
        val ourSize: Int = progress_main.width * teamOnePoss / 100
        progress_green.layoutParams = RelativeLayout.LayoutParams(ourSize, progress_green.height)
    }
//    Initializing Recycler View
    private fun initAdapter(matchResponse: MatchResponse?){
        recycler_view_id.layoutManager = LinearLayoutManager(context)
        recycler_view_id.adapter = MatchAdapter(matchResponse!!.match.matchSummary!!, context!!)
    }
}
