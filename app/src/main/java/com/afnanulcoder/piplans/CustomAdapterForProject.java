package com.afnanulcoder.piplans;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomAdapterForProject extends ArrayAdapter<ProjectDetails>
{
    private Activity context;
    private List<ProjectDetails> projectList;
    private ArrayList<ProjectDetails> arraylist;



    public CustomAdapterForProject(Activity context, List<ProjectDetails> projectList) {
        super(context, R.layout.recycleview_sample_view_layout, projectList);
        this.context = context;
        this.projectList = projectList;

        this.arraylist = new ArrayList<ProjectDetails>();
        this.arraylist.addAll(projectList);

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.recycleview_sample_view_layout, null, true);

        ProjectDetails projectDetails = projectList.get(position);

        TextView theTitle = view.findViewById(R.id.sampleTitleID);
        TextView theCategory = view.findViewById(R.id.sampleCategoryID);
        TextView theShortDescription = view.findViewById(R.id.sampleShortDescriptionID);



        theTitle.setText(projectDetails.getuTitle());
        theCategory.setText(projectDetails.getuCategory());
        theShortDescription.setText(projectDetails.getuShort());

        return view;
    }


    /*
    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        projectList.clear();
        if (charText.length() == 0) {
            projectList.addAll(arraylist);
        } else {
            for (ProjectDetails projectDetails : arraylist) {
                if (projectDetails.getuTitle().toLowerCase(Locale.getDefault()).contains(charText) || projectDetails.getuShort().toLowerCase(Locale.getDefault()).contains(charText) || projectDetails.getuCategory().toLowerCase(Locale.getDefault()).contains(charText)) {
                    projectList.add(projectDetails);
                }
            }
        }

        notifyDataSetChanged();
    }


     */


}
