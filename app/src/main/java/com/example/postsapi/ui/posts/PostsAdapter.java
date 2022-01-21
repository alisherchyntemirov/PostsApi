package com.example.postsapi.ui.posts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.postsapi.ItemOnClick;
import com.example.postsapi.data.models.Post;
import com.example.postsapi.databinding.ItemPostBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private HashMap<Integer, String> name = new HashMap<>();
    private List<Post> posts=new ArrayList<>();
    private ItemOnClick itemClick;

    public void setItemClick(ItemOnClick itemClick) {
        this.itemClick = itemClick;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        name.put(1,"Аделя");
        name.put(2,"Адеми");
        name.put(3,"Аделя");
        name.put(4,"Ринат");
        name.put(5,"Алым");
        name.put(6,"Алиаскар");
        name.put(7,"Баястан");
        name.put(8,"Рустам");
        name.put(9,"Алишер");
        name.put(10,"Алымбек");
        notifyDataSetChanged();
    }

    private ItemPostBinding binding;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding=ItemPostBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void removeItem(int position) {
        posts.remove(position);
        notifyItemRemoved(position);
    }

    public Post getPost(int position) {
        return posts.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemPostBinding binding;
        public ViewHolder(@NonNull ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding=binding;


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClick.click(getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    itemClick.longClick(getAdapterPosition());
                    return true;
                }
            });
        }

        public void onBind(Post post) {
            binding.tvUserId.setText(name.get(post.getUserId()));
            binding.tvTitle.setText(post.getTitle());
            binding.tvContent.setText(post.getContent());
            binding.tvGroup.setText(String.valueOf(post.getGroupId()));
        }
    }
}