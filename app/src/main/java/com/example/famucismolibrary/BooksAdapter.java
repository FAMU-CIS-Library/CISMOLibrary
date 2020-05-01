package com.example.famucismolibrary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder>{

    private Context context;
    private List<Book> posts;

    public BooksAdapter(Context context, List<Book> allPosts) {
        this.context = context;
        this.posts = allPosts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return this.posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout rvBooks;
        private TextView tvBookName;
        private TextView tvBookEdition;
        private TextView tvBookStatus;
        private ImageView ivBookCover;

        public ViewHolder (@NonNull View itemView)
        {
            super(itemView);
            tvBookName = itemView.findViewById(R.id.tvBookName);
            tvBookEdition = itemView.findViewById(R.id.tvBookEdition);
            tvBookStatus = itemView.findViewById(R.id.tvBookStatus);
            ivBookCover = itemView.findViewById(R.id.ivBookCover);
        }

        public void bind(Book post)
        {
            tvBookName.setText(post.getBookTitle());
            tvBookStatus.setText(post.getCondition());
            tvBookEdition.setText(post.getEdition());

            ParseFile img = post.getBookCover();

            if(img!= null)
            {
                Glide.with(context).load(post.getBookCover().getUrl()).into(ivBookCover);
            }

//            rvBooks.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    Intent i = new Intent(context, BookDetailActivity.class);
////                }
////            });
        }
    }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Book> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }
}