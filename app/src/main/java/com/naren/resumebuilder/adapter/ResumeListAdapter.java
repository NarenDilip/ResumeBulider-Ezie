package com.naren.resumebuilder.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.content.Intent;

import com.naren.resumebuilder.ui.DashboardScreen;
import com.naren.resumebuilder.ui.EditResumeScreen;
import com.naren.resumebuilder.ui.PreviewResumeScreen;
import com.naren.resumebuilder.utils.AppPreference;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.naren.resumebuilder.R;
import com.naren.resumebuilder.basepojo.PersonResumeData;

import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class ResumeListAdapter extends RecyclerView.Adapter<ResumeListAdapter.Viewholder> {

    private Context context;
    private ArrayList<PersonResumeData> courseModelArrayList;

    // Constructor
    public ResumeListAdapter(Context context, ArrayList<PersonResumeData> courseModelArrayList) {
        this.context = context;
        this.courseModelArrayList = courseModelArrayList;
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        TextView personName;
        TextView personDesignation;
        ImageView personImage;
        Button Edit;
        Button Preview;
        Button Delete;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            personName = (TextView) itemView.findViewById(R.id.person_names);
            personDesignation = (TextView) itemView.findViewById(R.id.person_designation);
            personImage = (ImageView) itemView.findViewById(R.id.personimageView);
            Edit = (Button) itemView.findViewById(R.id.updatebtn);
            Preview = (Button) itemView.findViewById(R.id.viewbtn);
            Delete = (Button) itemView.findViewById(R.id.deletebtn);
        }
    }

    @NonNull
    @Override
    public ResumeListAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResumeListAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        PersonResumeData model = courseModelArrayList.get(position);

        TextView textViewName = holder.personName;
        TextView textViewVersion = holder.personDesignation;
        ImageView imageView = holder.personImage;
        Button previewbutton = holder.Preview;
        Button editbutton = holder.Edit;
        Button deletebutton = holder.Delete;

        textViewName.setText(courseModelArrayList.get(position).getName());
        textViewVersion.setText(courseModelArrayList.get(position).getDesignation());

        if (courseModelArrayList.get(position).getImage().toString() != null) {
            Bitmap myBitmap = BitmapFactory.decodeFile(courseModelArrayList.get(position).getImage());
            imageView.setImageBitmap(myBitmap);
        }

        previewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courseModelArrayList.get(position).getResumeId();
                AppPreference.INSTANCE.put(context, "ResumeId", courseModelArrayList.get(position).getResumeId());
                Intent i = new Intent(context, PreviewResumeScreen.class);
                context.startActivity(i);

            }
        });

        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courseModelArrayList.get(position).getResumeId();
                AppPreference.INSTANCE.put(context, "ResumeId", courseModelArrayList.get(position).getResumeId());
                Intent intent = new Intent(context, EditResumeScreen.class);
                context.startActivity(intent);
            }
        });

        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courseModelArrayList.get(position).getResumeId();
                AppPreference.INSTANCE.put(context, "ResumeId", courseModelArrayList.get(position).getResumeId());
                AppPreference.INSTANCE.put(context, "DeleteCmd", "Yes");
                Intent intent = new Intent(context, DashboardScreen.class);
                context.startActivity(intent);
            }
        });


//        imageView.setImageResource(courseModelArrayList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return courseModelArrayList.size();
    }
}