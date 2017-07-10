package com.example.sampaniccia.learningthings.Adapters;


import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sampaniccia.learningthings.Activities.MainActivity;
import com.example.sampaniccia.learningthings.Objects.Comment;
import com.example.sampaniccia.learningthings.R;

import java.util.List;

public class DiscussionAdapter extends
        RecyclerView.Adapter<DiscussionAdapter.DiscussionViewHolder> {

    private List<Comment> comments;
    private Context context;

    private DialogFragment adminOptions;

    MainActivity mainAct;

    public DiscussionAdapter(Context c, List<Comment> list){
        comments = list;
        context = c;
    }

    public Context getContext(){
        return context;
    }

    @Override
    public DiscussionViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context c = parent.getContext();
        mainAct = (MainActivity) c;
        LayoutInflater inflater = LayoutInflater.from(c);

        View discussionView = inflater.inflate(R.layout.discussion_listview, parent, false);

        DiscussionViewHolder vH = new DiscussionViewHolder(discussionView);
        return vH;
    }



    @Override
    public void onBindViewHolder(DiscussionViewHolder viewHolder, int position){
        final Comment c = comments.get(position);

        viewHolder.itemView.setTag(R.id.drawNormalDivider, Boolean.TRUE);
        viewHolder.itemView.setTag(R.id.drawSpecialDivider, Boolean.FALSE);

        TextView name = viewHolder.tvName;
        TextView text = viewHolder.tvText;
        final ImageButton button = viewHolder.adminButton;

        name.setText(c.getName());
        text.setText(c.getText());
        button.setBackgroundResource(R.drawable.ic_more_vert_black_24dp);
        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                mainAct.adminMenu(button, c);
            }
        });
    }

    @Override
    public int getItemCount(){
        return comments.size();
    }

    public class DiscussionViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvText;
        public ImageButton adminButton;

        private View itemView;

        public DiscussionViewHolder(View itemView){
            super(itemView);
            this.itemView = itemView;

            tvName = (TextView) itemView.findViewById(R.id.event_dis_layout_txt_title);
            tvText = (TextView) itemView.findViewById(R.id.event_dis_layout_txt_text);
            adminButton = (ImageButton) itemView.findViewById(R.id.event_dis_layout_buttonAdmin);
        }
    }

}
