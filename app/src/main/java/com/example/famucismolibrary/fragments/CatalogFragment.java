package com.example.famucismolibrary.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.famucismolibrary.Book;
import com.example.famucismolibrary.BooksAdapter;
import com.example.famucismolibrary.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CatalogFragment extends Fragment {

    public static final String TAG = "CatalogFragment";
    protected RecyclerView rvBooks;
    protected BooksAdapter adapter;
    protected List<Book> allBooks;

    public CatalogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_catalog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvBooks = view.findViewById(R.id.rvBooks);
        allBooks = new ArrayList<Book>();
        adapter = new BooksAdapter(getContext(), allBooks);
        rvBooks.setAdapter(adapter);
        rvBooks.setLayoutManager(new LinearLayoutManager(getContext()));
        queryBooks();;
    }

    private void queryBooks() {
        ParseQuery<Book> bookQuery = new ParseQuery<Book>(Book.class);
        bookQuery.setLimit(20);
        bookQuery.addDescendingOrder(Book.KEY_BOOKTITLE);
        bookQuery.findInBackground(new FindCallback<Book>() {
            @Override
            public void done(List<Book> books, ParseException e) {
                if(e != null)
                {
                    Log.e(TAG, "Error retrieving book");
                    e.printStackTrace();
                    return;
                }

                for (int i = 0; i < books.size(); i++)
                    Log.d(TAG, "Book: " + books.get(i).getBookTitle() );

                adapter.clear();
                allBooks.addAll(books);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
