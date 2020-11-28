package com.taskdemo.Adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taskdemo.QuestionDetail;
import com.taskdemo.R;
import com.taskdemo.model.Question;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class QAdapter extends RecyclerView.Adapter<QAdapter.ProductViewHolder> {
    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Question> productList;



    public QAdapter(Context mCtx,List<Question> productList)
    {
        this.mCtx=mCtx;
        this.productList=productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_post_layout, parent, false);
        return new QAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

    Question product=productList.get(position);

    holder.titletag.setText(product.getQtitle());
    holder.topictag.setText(product.getQtopic());
    holder.jobtag.setText(product.getQjob());
    holder.interviewtag.setText(product.getQinterview());
    holder.companytag.setText(product.getQcompany());
    holder.collegetag.setText(product.getQcollege());
    holder.uptag.setText(product.getQup().toString());
    holder.content.setText(product.getQcontent());
    holder.seentag.setText(product.getQid().toString());





    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView companytag,interviewtag,jobtag,topictag,titletag,uptag,seentag,collegetag,content;
        TextView post_user,prof_icon;
        LinearLayout container;

        public ProductViewHolder(View itemView) {
            super(itemView);

            container=itemView.findViewById(R.id.postrel);
            companytag=itemView.findViewById(R.id.post_company);
            interviewtag=itemView.findViewById(R.id.interview_tag);
            jobtag=itemView.findViewById(R.id.job_tag);
            topictag=itemView.findViewById(R.id.topic_tag);
            titletag=itemView.findViewById(R.id.title_tag);
            collegetag=itemView.findViewById(R.id.post_college);
            uptag=itemView.findViewById(R.id.nup);
            content=itemView.findViewById(R.id.content_tag);
            seentag=itemView.findViewById(R.id.ndown);

            post_user = itemView.findViewById(R.id.post_username);
            prof_icon=itemView.findViewById(R.id.prof_icon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), QuestionDetail.class);
                    intent.putExtra("title",titletag.getText().toString());
                    intent.putExtra("content",content.getText().toString());
                    intent.putExtra("qid",seentag.getText().toString());
                    intent.putExtra("up",uptag.getText().toString());

                    try
                    {
                        //ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation((Activity)mCtx,pairs);

                        v.getContext().startActivity(intent);}//,options.toBundle());}
                    catch(Exception e)
                    {
                        //ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(,pairs);

                        v.getContext().startActivity(intent);
                    }


                }
            });

        }


    }

}
