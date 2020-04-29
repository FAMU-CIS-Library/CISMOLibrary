package com.example.famucismolibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.famucismolibrary.fragments.CatalogFragment;
import com.example.famucismolibrary.fragments.ProfileFragment;
import com.example.famucismolibrary.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private Button btnPush;
    private BottomNavigationView bottomNav;
    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottomNavigation);

        queryBooks();

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        fragment = new CatalogFragment();
                        Toast.makeText(MainActivity.this, "Catalog", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.action_search:
                        fragment = new SearchFragment();
                        Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.action_profile:
                    default:
                        fragment = new ProfileFragment();
                        Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_LONG).show();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
    }

    private void queryBooks() {
        ParseQuery<Book> bookQuery = new ParseQuery<Book>(Book.class);
        // bookQuery.include(Book.KEY_RENTEDBY);
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
            }
        });
    }
}
