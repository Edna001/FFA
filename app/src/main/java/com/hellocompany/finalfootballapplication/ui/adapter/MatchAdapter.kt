package com.hellocompany.finalfootballapplication.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hellocompany.finalfootballapplication.R
import com.hellocompany.finalfootballapplication.service.enums.GoalType
import com.hellocompany.finalfootballapplication.service.enums.MatchActionType
import com.hellocompany.finalfootballapplication.service.model.MatchSummary
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.goal_view.view.*
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlinx.android.synthetic.main.substitution_view.view.*

class MatchAdapter(private val item: MatchSummary, private var context: Context):
        RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return item.summaries!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val summary = item.summaries?.get(position)

        holder.itemView.left_side.removeAllViews()
        holder.itemView.right_side.removeAllViews()

        for (team1Action in summary?.team1Action?: arrayListOf()){
            var view = View.inflate(context, R.layout.goal_view, null)

            when(team1Action.actionType){
                MatchActionType.GOAL.value -> {
                    view.playerNameId.text = team1Action.action.player?.playerName
                    Picasso.get().load(team1Action.action.player?.playerImage).into(view.playerImage)
                    when(team1Action.action.goalType){
                        GoalType.GOAL.value -> view.ballImage.setImageResource(R.drawable.ic_ball_green)
                        GoalType.OWN_GOAL.value -> view.ballImage.setImageResource(R.drawable.ic_ball_red)
                    }
                }
                MatchActionType.YELLOW_CARD.value -> {
                    view.playerNameId.text = team1Action.action.player?.playerName
                    Picasso.get().load(team1Action.action.player?.playerImage).into(view.playerImage)
                    view.ballImage.setImageResource(R.drawable.ic_card_yellow)
                }
                MatchActionType.RED_CARD.value -> {
                    view.playerNameId.text = team1Action.action.player?.playerName
                    view.ballImage.setImageResource(R.drawable.ic_card_red)
                    Picasso.get().load(team1Action.action.player?.playerImage).into(view.playerImage)
                }
                MatchActionType.SUBSTITUTION.value -> {
                    view = View.inflate(context, R.layout.substitution_view, null)
                    view.sub_player_one_name.text = team1Action.action.player1?.playerName
                    view.sub_player_two_name.text = team1Action.action.player2?.playerName
                    Picasso.get().load(team1Action.action.player1?.playerImage).into(view.sub_player_one_ic)
                    Picasso.get().load(team1Action.action.player2?.playerImage).into(view.sub_player_two_ic)
                }
            }
            holder.itemView.left_side.addView(view)
        }

        for (team2Action in summary?.team2Action?: arrayListOf()){
            var view = View.inflate(context, R.layout.goal_view, null)

            view.scaleX = -1f
            view.player_name.scaleX = -1f
            when(team2Action.actionType){
                MatchActionType.GOAL.value -> {
                    view.playerNameId.text = team2Action.action.player?.playerName
                    Picasso.get().load(team2Action.action.player?.playerImage).into(view.playerImage)
                    when(team2Action.action.goalType){
                        GoalType.GOAL.value -> view.ballImage.setImageResource(R.drawable.ic_ball_green)
                        GoalType.OWN_GOAL.value -> view.ballImage.setImageResource(R.drawable.ic_ball_red)
                    }
                }
                MatchActionType.YELLOW_CARD.value -> {
                    view.playerNameId.text = team2Action.action.player?.playerName
                    Picasso.get().load(team2Action.action.player?.playerImage).into(view.playerImage)
                    view.ballImage.setImageResource(R.drawable.ic_card_yellow)
                }
                MatchActionType.RED_CARD.value -> {
                    view.playerNameId.text = team2Action.action.player?.playerName
                    view.ballImage.setImageResource(R.drawable.ic_card_red)
                    Picasso.get().load(team2Action.action.player?.playerImage).into(view.playerImage)
                }
                MatchActionType.SUBSTITUTION.value -> {
                    view = View.inflate(context, R.layout.substitution_view, null)
                    view.scaleX = -1f
                    view.sub_player_names.scaleX = -1f
                    view.sub_player_one_name.text = team2Action.action.player1?.playerName
                    view.sub_player_two_name.text = team2Action.action.player2?.playerName
                    Picasso.get().load(team2Action.action.player1?.playerImage).into(view.sub_player_one_ic)
                    Picasso.get().load(team2Action.action.player2?.playerImage).into(view.sub_player_two_ic)
                }
            }
            holder.itemView.right_side.addView(view)
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)

//    data class Mapper(var name: String, var imageUrl: String){
//        fun from(form: MatchSummary){
//
//        }
//    }
}