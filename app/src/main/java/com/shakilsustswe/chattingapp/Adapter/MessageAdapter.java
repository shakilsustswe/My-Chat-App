package com.shakilsustswe.chattingapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.shakilsustswe.chattingapp.ModelClass.Messages;
import com.shakilsustswe.chattingapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.shakilsustswe.chattingapp.Activity.MessageActivity.rImage;
import static com.shakilsustswe.chattingapp.Activity.MessageActivity.sImage;

public class MessageAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Messages> messagesArrayList;
    int ITEM_SEND = 1;
    int ITEM_RECIVE = 2;

    public MessageAdapter(Context context, ArrayList<Messages> messagesArrayList) {
        this.context = context;
        this.messagesArrayList = messagesArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_SEND) {
            View view = LayoutInflater.from(context).inflate(R.layout.sender_text_layout, parent, false);
            return new SenderViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.reciver_text_layout, parent, false);
            return new ReciverViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Messages messages = messagesArrayList.get(position);

        if (holder.getClass() == SenderViewHolder.class) {

            SenderViewHolder viewHolder = (SenderViewHolder) holder;
            viewHolder.txtmessage.setText(messages.getMessage());
            Picasso.get().load(sImage).into(viewHolder.circleImageView);

        } else {
            ReciverViewHolder viewHolder = (ReciverViewHolder) holder;
            viewHolder.txtmessage.setText(messages.getMessage());
            Picasso.get().load(rImage).into(viewHolder.circleImageView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Messages messages = messagesArrayList.get(position);
        if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(messages.getSenderId())) {
            return ITEM_SEND;
        } else {
            return ITEM_RECIVE;
        }
    }

    @Override
    public int getItemCount() {
        return messagesArrayList.size();
    }

    class SenderViewHolder extends RecyclerView.ViewHolder {

        CircleImageView circleImageView;
        TextView txtmessage;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.profile_image);
            txtmessage = itemView.findViewById(R.id.txtMessages);

        }
    }

    class ReciverViewHolder extends RecyclerView.ViewHolder {

        CircleImageView circleImageView;
        TextView txtmessage;

        public ReciverViewHolder(@NonNull View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.profile_image);
            txtmessage = itemView.findViewById(R.id.txtMessages);

        }
    }

}
