package com.hellocompany.finalfootballapplication.service.model.response

import com.hellocompany.finalfootballapplication.service.model.Match

data class MatchResponse(val resultCode: Int? = 0,
                         val match: Match
)