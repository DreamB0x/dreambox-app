package wilderpereira.com.dreambox

/**
 * Created by Wilder on 02/12/17.
 */
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.request.ImageRequestBuilder
import kotlinx.android.synthetic.main.item_goal.view.*
import wilderpereira.com.dreambox.model.Goal

/**
 * Created by Wilder on 17/10/17.
 */

class GoalsAdapter(private var goals: List<Goal>) : RecyclerView.Adapter<GoalsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_goal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(goals[position])
    }

    override fun getItemCount() = goals.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(goal: Goal) {
            with(goal){
                //TODO: get default text form somewhere
                itemView.goalNameTv.text = name
                itemView.target.text = "R$ "+"%.2f".format(value)
                itemView.deposited.text = "R$ "+"%.2f".format(amaoutDeposited)
                Log.d("test", "image: "+image)
                //TODO: check if cant load image
                val uri = Uri.parse(image)
                val request = ImageRequestBuilder.newBuilderWithSource(uri)
                        .setProgressiveRenderingEnabled(true)
                        .build()
                val draweeController = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .build()

                itemView.image.controller = draweeController
            }
        }
    }

}