package com.hellocompany.finalfootballapplication.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.hellocompany.finalfootballapplication.R
import kotlinx.android.synthetic.main.team_view.view.*

class TeamView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    var teamName: String? = null
        set(value) {
            field = value
            if (value != null)
                teamNameLabel?.text = value
        }

    init {
        View.inflate(context, R.layout.team_view, this)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.TeamView, 0, 0)
            teamName = typedArray.getString(R.styleable.TeamView_name)
            typedArray.recycle()
        }
    }

//    @BindingAdapter("imageUrl")
//    fun ImageView.setImageUrl(url: String?){
//        this.tag = url?: ""
//        if (!url.isNullOrEmpty())
//            Picasso.get().load(url).into(this)
//    }
//    @InverseBindingAdapter(attribute = "imageUrl")
//    fun getImageUrl(url: ImageView?): String{
//        return url?.tag as String
//    }
//    @BindingAdapter(value = ["imageUrlAttrChanged"])
//    fun setListener(url: ImageView?, listener: InverseBindingListener){
//        if (listener != null){
//            listener.onChange()
//        }
//    }
}