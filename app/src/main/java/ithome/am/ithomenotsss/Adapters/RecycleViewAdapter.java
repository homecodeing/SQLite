package ithome.am.ithomenotsss.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ithome.am.ithomenotsss.Activitys.ProfileActivity;
import ithome.am.ithomenotsss.Engine.Engine;
import ithome.am.ithomenotsss.Engine.Model;
import ithome.am.ithomenotsss.R;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    Engine engine;
    private List<Model> mPeopleList;
    private Context mContext;
    private RecyclerView mRecyclerV;
    public RecycleViewAdapter(List<Model> myDataset, Context context, RecyclerView recyclerView) {
        mPeopleList = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }
    @NonNull
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.single_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Model person = mPeopleList.get(position);
        holder.personNameTxtV.setText("Name: " + person.getName());
        holder.personOccupationTxtV.setText("Occupation: " + person.getOccupation());
        Picasso.with(mContext).load(person.getImage()).placeholder(R.mipmap.ic_launcher).into(holder.personImageImgV);

        //listen to single view layout click
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Choose option");
                builder.setMessage("Update or delete user?");
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //go to update activity
                        goToUpdateActivity(person.getId());

                    }
                });
                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        PersonDBHelper dbHelper = new PersonDBHelper(mContext);
//                        dbHelper.deletePersonRecord(person.getId(), mContext);
                       engine=Engine.getInstance();
                       engine.getDbServices(mContext).deletePersonRecord(person.getId(),mContext);
                        mPeopleList.remove(position);
                        mRecyclerV.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, mPeopleList.size());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
    }
    private void goToUpdateActivity(long personId){
        Intent goToUpdate = new Intent(mContext, ProfileActivity.class);
        goToUpdate.putExtra("USER_ID", personId);
        mContext.startActivity(goToUpdate);
    }

    @Override
    public int getItemCount() {
        return mPeopleList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView personNameTxtV;
        //public TextView personAgeTxtV;
        public TextView personOccupationTxtV;
        public ImageView personImageImgV;


        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            personNameTxtV =  v.findViewById(R.id.name);
          //  personAgeTxtV = (TextView) v.findViewById(R.id.age);
            personOccupationTxtV =  v.findViewById(R.id.occupation);
            personImageImgV = (ImageView) v.findViewById(R.id.image);




        }
    }









}
