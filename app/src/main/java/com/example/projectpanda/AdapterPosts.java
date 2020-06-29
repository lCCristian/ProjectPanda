package com.example.projectpanda;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdapterPosts extends RecyclerView.Adapter<AdapterPosts.MyHolder> {


    Context context;
    List<ModelPost> postList;

    public AdapterPosts(Context context, List<ModelPost> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //inflate layout row_post.xml
        View view = LayoutInflater.from(context).inflate(R.layout.row_posts,viewGroup,false);


        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        //get data
        String uid = postList.get(i).getUid();
        String uEmail = postList.get(i).getuEmail();
        String uName = postList.get(i).getuName();
        String uDp = postList.get(i).getuDp();
        String pID = postList.get(i).getpID();
        String pTitle = postList.get(i).getpTitle();
        String pDescription = postList.get(i).getpDescription();
        String pImage = postList.get(i).getpImage();
        String pTimeStamp = postList.get(i).getpTime();

        //convert timestamp to dd/mm/yy hh:mm am/pm

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(pTimeStamp));
        String pTime = DateFormat.format("dd/MM/yyyy hh:mm aa",calendar).toString();

        // set data
        myHolder.uNameTV.setText(uName);
        myHolder.pTimeTV.setText(pTime);
        myHolder.pTitleTV.setText(pTime);
        myHolder.pDescriptionTV.setText(pDescription);




        //set user dp
        try{
            Picasso.get().load(uDp).placeholder(R.drawable.ic_default_img).into(myHolder.uPictureIV);
        }catch (Exception e){


        }

        //set post image
        //if there is no image - hide the image view
        if (pImage.equals("noImage")){
            //hide imageview
            myHolder.pImageIV.setVisibility(View.GONE);
        }else{
            try{
                Picasso.get().load(pImage).into(myHolder.pImageIV);
            }catch (Exception e){

            }
        }



        //handle button clicks

//        myHolder.moreBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // will implement later
//                Toast.makeText(context,"More",Toast.LENGTH_SHORT).show();
//            }
//        });
//        myHolder.likeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // will implement later
//                Toast.makeText(context,"Like",Toast.LENGTH_SHORT).show();
//            }
//        });
//        myHolder.commentBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // will implement later
//                Toast.makeText(context,"Comment",Toast.LENGTH_SHORT).show();
//            }
//        });
//        myHolder.shareBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // will implement later
//                Toast.makeText(context,"Share",Toast.LENGTH_SHORT).show();
//            }
//        });
//






    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    //view holder class

    class MyHolder extends RecyclerView.ViewHolder{

        //views from roq_post.xml
        ImageView uPictureIV,pImageIV;
        TextView uNameTV, pTimeTV, pTitleTV, pDescriptionTV, pLikesTV;
       // ImageButton moreBtn;
        Button likeBtn, commentBtn, shareBtn;




        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //init views
            uPictureIV = itemView.findViewById(R.id.uPictureIV);
            pImageIV = itemView.findViewById(R.id.pImageIV);
            uNameTV = itemView.findViewById(R.id.uNameTV);
            pTimeTV = itemView.findViewById(R.id.pTimeTV);
            pTitleTV = itemView.findViewById(R.id.pTitleTV);
            pDescriptionTV = itemView.findViewById(R.id.pDescriptionTV);
          //  moreBtn = itemView.findViewById(R.id.moreBtn);
           // likeBtn = itemView.findViewById(R.id.likeBtn);
          //  commentBtn = itemView.findViewById(R.id.commentBtn);
           // shareBtn = itemView.findViewById(R.id.shareBtn);
          //  pLikesTV = itemView.findViewById(R.id.pLikesTV);


        }
    }




}
