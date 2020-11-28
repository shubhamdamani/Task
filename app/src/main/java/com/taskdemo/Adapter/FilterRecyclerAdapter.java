package com.taskdemo.Adapter;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.taskdemo.R;
import com.taskdemo.model.MainFilterModel;

import java.util.ArrayList;


public class FilterRecyclerAdapter extends RecyclerView.Adapter<FilterRecyclerAdapter.PersonViewHolder> {

    private final int resource;
    private final FragmentActivity context;
    ArrayList<MainFilterModel> filterModels;
    OnItemClickListener mItemClickListener;


    public FilterRecyclerAdapter(FragmentActivity context, int filter_list_item_layout, ArrayList<MainFilterModel> filterModels) {
        this.context = context;
        this.filterModels = filterModels;
        this.resource = filter_list_item_layout;
    }



    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(this.context)
                .inflate(resource, viewGroup, false);
        return new PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {

        personViewHolder.parentView.setSelected(filterModels.get(i).isSelected());
        String title=filterModels.get(i).getTitle();
        if (personViewHolder.personName.isSelected()) {
            if (title.equals("Company")) {
                personViewHolder.personName.setCompoundDrawablesWithIntrinsicBounds( 0, R.drawable.companyr, 0,0 );
                personViewHolder.personName.setText(title);
            } else if (title.equals("Topics")) {
                personViewHolder.personName.setCompoundDrawablesWithIntrinsicBounds( 0,R.drawable.topicr, 0, 0);
                personViewHolder.personName.setText(title);
            } else if (title.equals("InterviewType")) {
                personViewHolder.personName.setCompoundDrawablesWithIntrinsicBounds( 0,R.drawable.interviewr, 0, 0);
                personViewHolder.personName.setText(title);
            } else if (title.equals("College")) {
                personViewHolder.personName.setCompoundDrawablesWithIntrinsicBounds( 0,R.drawable.coleger, 0, 0);
                personViewHolder.personName.setText(title);
            } else if (title.equals("JobType")) {
                personViewHolder.personName.setCompoundDrawablesWithIntrinsicBounds( 0,R.drawable.jobr, 0, 0);
                personViewHolder.personName.setText(title);
            }
        } else {
            if (filterModels.get(i).getTitle().equals("Company")) {
                personViewHolder.personName.setCompoundDrawablesWithIntrinsicBounds( 0,R.drawable.companyr, 0, 0);
                personViewHolder.personName.setText(title);
            } else if (filterModels.get(i).getTitle().equals("Topics")) {
                personViewHolder.personName.setCompoundDrawablesWithIntrinsicBounds( 0,R.drawable.topicr, 0, 0);
                personViewHolder.personName.setText(title);
            } else if (filterModels.get(i).getTitle().equals("InterviewType")) {
                personViewHolder.personName.setCompoundDrawablesWithIntrinsicBounds( 0, R.drawable.interviewr, 0, 0);
                personViewHolder.personName.setText(title);
            }else if (filterModels.get(i).getTitle().equals("College")) {
                personViewHolder.personName.setCompoundDrawablesWithIntrinsicBounds( 0,R.drawable.coleger, 0, 0);
                personViewHolder.personName.setText(title);
            } else if (filterModels.get(i).getTitle().equals("JobType")) {
                personViewHolder.personName.setCompoundDrawablesWithIntrinsicBounds( 0, R.drawable.jobr, 0, 0);
                personViewHolder.personName.setText(title);
            }
        }


    }

    @Override
    public int getItemCount() {
        return filterModels.size();
    }

    public void setItemSelected(int position) {
        for (MainFilterModel filterModel : filterModels) {
            filterModel.setIsSelected(false);


        }
       if (position != -1) {
            filterModels.get(position).setIsSelected(true);
            notifyDataSetChanged();
        }

    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position);


    }

    public class PersonViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        TextView personName;
        public View parentView;

        PersonViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            personName = (TextView) itemView.findViewById(R.id.txt_item_list_title);
            parentView = itemView;

        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }
    }


}

