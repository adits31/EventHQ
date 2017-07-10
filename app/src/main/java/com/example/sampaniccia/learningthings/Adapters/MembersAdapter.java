package com.example.sampaniccia.learningthings.Adapters;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sampaniccia.learningthings.Activities.MainActivity;
import com.example.sampaniccia.learningthings.Objects.Member;
import com.example.sampaniccia.learningthings.R;

import java.util.List;


public class MembersAdapter extends
        RecyclerView.Adapter<MembersAdapter.MembersViewHolder> {

    protected List<Member> members;
    protected Context context;

    private DialogFragment adminOptions; //Add later

    protected MainActivity mainAct;

    public MembersAdapter(Context c, List<Member> list){
        context = c;
        members = list;
    }

    @Override
    public MembersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context c = parent.getContext();
        mainAct = (MainActivity) c;
        LayoutInflater inflater = LayoutInflater.from(c);

        View view = inflater.inflate(R.layout.member_listview, parent, false);
        MembersViewHolder vH = new MembersViewHolder(view);
        return vH;
    }

    @Override
    public void onBindViewHolder(MembersAdapter.MembersViewHolder holder, int position) {
        final Member m = members.get(position);

        holder.itemView.setTag(R.id.drawNormalDivider, Boolean.TRUE);
        holder.itemView.setTag(R.id.drawSpecialDivider, Boolean.FALSE);

        TextView name = holder.name;
        TextView desc = holder.description;
        ImageView prof = holder.profImage;

        final ImageButton button = holder.buttonAdmin;

        name.setText(m.getName());
        desc.setText(m.getDesc());

        prof.setImageDrawable(m.getProf());

        button.setBackgroundResource(R.drawable.ic_more_vert_black_24dp);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mainAct.adminMenu(button,m);
            }
        });

    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public class MembersViewHolder extends RecyclerView.ViewHolder {

        protected TextView name;
        protected TextView description;
        protected ImageView profImage;
        protected ImageButton buttonAdmin;

        protected View itemView;

        public MembersViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            name = (TextView) itemView.findViewById(R.id.list_title);
            description = (TextView) itemView.findViewById(R.id.list_desc);
            profImage = (ImageView) itemView.findViewById(R.id.list_avatar);
            buttonAdmin = (ImageButton) itemView.findViewById(R.id.event_mem_layout_buttonAdmin);
        }

    }

}
