package com.abhishek.retrofit_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;

    private JsonPlaceHolderApi jsonPlaceHolderApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


         jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


        getPosts();

        //getComments();

    }


    private void getPosts()
    {
        Map<String,String> parameters = new HashMap<>();
        parameters.put("userId" ,"2");
        parameters.put("_sort" , "id");
        parameters.put("_order", "desc");



       // Call<List<Post>> call = jsonPlaceHolderApi.getdata(new Integer[]{1,2,3,4},"id","aesc");

        Call<List<Post>> call = jsonPlaceHolderApi.getdata(parameters);

        //we cannot execute it on the main thread It will freeze the app
        //this enqueue performs all task in background
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response)
            {
                if (!response.isSuccessful())
                {
                    textViewResult.setText("Code:"+response.code());
                    return;

                }
                List<Post> posts = response.body();

                for (Post post: posts)
                {
                    String content = "";
                    content += "User Id:" + post.getUserId() + "\n";
                    content += "ID: " + post.getId() + "\n";
                    content += "Title" + post.getTitle() + "\n";
                    content += "Text" + post.getText() + "\n\n";

                    textViewResult.append(content);

                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t)
            {
                //throwable is super class of exception and error
                textViewResult.setText(t.getMessage());
                Toast.makeText(MainActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void getComments()
    {
        Call<List<Comment>> call = jsonPlaceHolderApi.getComments(3 );


        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response)
            {
                if (!response.isSuccessful())
                {
                    textViewResult.setText("Error Code "+response.code());
                    return;
                }
                List<Comment> comments = response.body();

                for (Comment comment: comments)
                {
                    String getComment = "";
                    getComment += "ID"+ comment.getId() +"\n";
                    getComment += "Post Id" +comment.getPostId() + "\n";
                    getComment += " Name"+ comment.getName() + "\n";
                    getComment += "Email"+ comment.getEmail() + "\n";
                    getComment += "text"+comment.getText() + "\n";


                    textViewResult.append(getComment);
                }


            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t)
            {
                textViewResult.setText(t.getMessage());


            }
        });

    }

}
