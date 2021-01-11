package com.imran.room_databasemvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imran.room_databasemvvm.R;
import com.imran.room_databasemvvm.database.Users;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserAdapterVH>
{
    private List<Users> usersList;
    private Context context;
    private ClickListner clickListner;

    public UsersAdapter(ClickListner clickListner)
    {
        this.clickListner=clickListner;
    }

    public void setData(List<Users> usersList)
    {
        this.usersList=usersList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new UserAdapterVH(
                LayoutInflater.from(context).inflate(R.layout.row_items,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterVH holder, int position)
    {
        final Users users=usersList.get(position);
        String username=users.getUsername();

        holder.tvName.setText(username);

        holder.imageOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                showPopUp(view,users);
            }
        });
    }

    public void showPopUp(View view, final Users users)
    {
        PopupMenu popupMenu=new PopupMenu(context,view);
        popupMenu.inflate(R.menu.menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                int id=menuItem.getItemId();
                switch (id)
                {
                    case R.id.itupdate:
                        clickListner.updateClicked(users);
                        break;
                    case R.id.itdelete:
                        clickListner.deleteClicked(users);
                        break;
                }
                return false;
            }
        });

        popupMenu.show();
    }

    public interface ClickListner
    {
        void updateClicked(Users users);
        void deleteClicked(Users users);
    }
    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class UserAdapterVH extends RecyclerView.ViewHolder
    {
        ImageView imageOption;
        TextView tvName;
        public UserAdapterVH(@NonNull View itemView) {
            super(itemView);
            imageOption=itemView.findViewById(R.id.imageOptions);
            tvName=itemView.findViewById(R.id.tvname);
        }
    }
}
