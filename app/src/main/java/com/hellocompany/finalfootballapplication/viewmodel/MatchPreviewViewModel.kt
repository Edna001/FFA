package com.hellocompany.finalfootballapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hellocompany.finalfootballapplication.service.model.response.MatchResponse
import com.hellocompany.finalfootballapplication.service.repository.MatchRepository

class MatchPreviewViewModel : ViewModel() {

    private val mMatchRepository: MatchRepository = MatchRepository().getInstance()
    private val mMatchResponse: MutableLiveData<MatchResponse> = mMatchRepository.getMatches()
    var stadiumAddress: MutableLiveData<String> = MutableLiveData()
    var teamOnePoss: MutableLiveData<String> = MutableLiveData()
    var teamTwoPoss: MutableLiveData<String> = MutableLiveData()
    var matchScore: MutableLiveData<String> = MutableLiveData()
    var matchTime: MutableLiveData<String> = MutableLiveData()

    init {
        mMatchResponse.observeForever {
            stadiumAddress.value = "${it.match.matchDate} ${it.match.stadiumAdress}"
            teamOnePoss.value = it.match.team1!!.ballPosition.toString()
            teamTwoPoss.value = it.match.team2!!.ballPosition.toString()
            matchScore.value = "${it.match.team1.score} : ${it.match.team2.score}"
            matchTime.value = it.match.matchTime.toString()
        }
    }

    fun getApiResponse(): LiveData<MatchResponse>{
        return mMatchResponse
    }
}
