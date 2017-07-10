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
import com.example.sampaniccia.learningthings.Objects.Announcement;
import com.example.sampaniccia.learningthings.R;

import java.util.List;

public class AnnouncementAdapter extends
        RecyclerView.Adapter<AnnouncementAdapter.AnnouncementsViewHolder> {

    private List<Announcement> announcements;
    private Context context;

    DialogFragment adminOptions;

    MainActivity mainAct;

    public AnnouncementAdapter(Context c, List<Announcement> list){
        announcements = list;
        context = c;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public AnnouncementsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context c = parent.getContext();
        mainAct = (MainActivity) c;
        LayoutInflater inflater = LayoutInflater.from(c);

        View announcementView = inflater.inflate(R.layout.announcements_listview, parent, false);

        AnnouncementsViewHolder vH = new AnnouncementsViewHolder(announcementView);
        return vH;
    }

    @Override
    public void onBindViewHolder(AnnouncementsViewHolder viewHolder, int position){
        final Announcement a = announcements.get(position);

        boolean drawNormalDivider = a.getDrawNormalDivider();
        if(drawNormalDivider){
            viewHolder.itemView.setTag(R.id.drawNormalDivider, Boolean.TRUE);
            viewHolder.itemView.setTag(R.id.drawSpecialDivider, Boolean.FALSE);
        }
        else {
            viewHolder.itemView.setTag(R.id.drawSpecialDivider, Boolean.TRUE);
            viewHolder.itemView.setTag(R.id.drawNormalDivider, Boolean.FALSE);
        }

        TextView name = viewHolder.tvName;
        TextView text = viewHolder.tvText;
        final ImageButton button = viewHolder.buttonAdmin;


        name.setText(a.getTitle());
        text.setText(a.getText());
        button.setBackgroundResource(R.drawable.ic_more_vert_black_24dp);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mainAct.adminMenu(button, a);
            }
        });

    }

    @Override
    public int getItemCount(){
        return announcements.size();
    }


    public class AnnouncementsViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvText;
        public ImageButton buttonAdmin;

        private View itemView;

        public AnnouncementsViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            tvName = (TextView) itemView.findViewById(R.id.event_ann_layout_txt_title);
            tvText = (TextView) itemView.findViewById(R.id.event_ann_layout_txt_text);
            buttonAdmin = (ImageButton) itemView.findViewById(R.id.event_ann_layout_buttonAdmin);
        }
    }
}
