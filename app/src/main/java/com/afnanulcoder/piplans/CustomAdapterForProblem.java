package com.afnanulcoder.piplans;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomAdapterForProblem extends ArrayAdapter<ProblemDetails> {
    private Activity context;
    private List<ProblemDetails> problemList;



    public CustomAdapterForProblem(Activity context, List<ProblemDetails> problemList) {
        super(context, R.layout.recycleview_sample_view_layout, problemList);
        this.context = context;
        this.problemList = problemList;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.recycleview_sample_view_layout, null, true);

        ProblemDetails problemDetails = problemList.get(position);

        TextView theTitle = view.findViewById(R.id.sampleTitleID);
        TextView theCategory = view.findViewById(R.id.sampleCategoryID);
        TextView theShortDescription = view.findViewById(R.id.sampleShortDescriptionID);



        theTitle.setText(problemDetails.getsTitle());
        theCategory.setText(problemDetails.getsCategory());
        theShortDescription.setText(problemDetails.getsShort());

        return view;
    }

}
