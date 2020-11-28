package com.taskdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dmax.dialog.SpotsDialog;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.taskdemo.Adapter.QAdapter;
import com.taskdemo.model.Filter;
import com.taskdemo.model.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class ViewActivity extends AppCompatActivity {

    Button filterBtn;

    String JSON_String,json_string;
    JSONArray jsonArray;
    JSONObject jsonObject;
    List<Question> mPostContent;
    QAdapter adapter;
    SpotsDialog dialog;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        dialog=new SpotsDialog(ViewActivity.this);
        dialog.show();
        recyclerView=findViewById(R.id.post_recycler);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        filterBtn=findViewById(R.id.filter_btn);
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ViewActivity.this,FilterActivity.class);
                startActivity(i);
                finish();
            }
        });

        new ViewActivity.BackgroundTask().execute();


    }
    public void fun()                   //PARSING JSON OBJECT TO NORMAL STRING AND SHIFTING TO CARDVIEW
    {
        json_string=JSON_String;

        String QContent,JobType,Company,InterviewType,College,Topic,Upvote,QTitle;
        Integer Upvotes,QuestionId;


        mPostContent =new ArrayList<>();

        try {
            jsonObject=new JSONObject(JSON_String);

            int count=0;
            jsonArray=jsonObject.getJSONArray("server_response");       //THIS IS NAME OF OUR JSON ARRAY

            while(count<jsonArray.length())
            {
                JSONObject jo=jsonArray.getJSONObject(count);  // ARRAY SUB-TAG, i.e KEY OF REQIRED VALUE
                QuestionId=jo.getInt("QuestionId");
                QContent=jo.getString("QContent");
                JobType=jo.getString("JobType");
                Company=jo.getString("Company");
                InterviewType=jo.getString("InterviewType");
                College=jo.getString("College");
                Topic=jo.getString("Topic");
                Upvotes=jo.getInt("Upvote");
                QTitle=jo.getString("QTitle");
                Question contacts=new Question(QTitle,QContent,Topic,Company,InterviewType,JobType,College,Upvotes,QuestionId);
                 mPostContent.add(contacts);
               adapter = new QAdapter(ViewActivity.this, mPostContent);       //ONE BY ONE PUSHING QUESTIONS TO CARDVIEW
                recyclerView.setAdapter(adapter);
                count++;


            }
            //dialog.dismiss();

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    class BackgroundTask extends AsyncTask<Void,Void,String>
    {
        String json_url="https://vlearndroidrun.000webhostapp.com/getDemo.php";


        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url=new URL(json_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data=
                        URLEncoder.encode("JobType", "UTF-8") + "=" + URLEncoder.encode(Filter.job, "UTF-8")+"&"+
                        URLEncoder.encode("Company", "UTF-8") + "=" + URLEncoder.encode(Filter.company, "UTF-8")+"&"+
                        URLEncoder.encode("InterviewType", "UTF-8") + "=" + URLEncoder.encode(Filter.interview, "UTF-8")+"&"+
                        URLEncoder.encode("College", "UTF-8") + "=" + URLEncoder.encode(Filter.college, "UTF-8")+"&"+
                        URLEncoder.encode("Topic", "UTF-8") + "=" + URLEncoder.encode(Filter.topic, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();

                while((json_string=bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(json_string+"\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                JSON_String=stringBuilder.toString().trim();
                return JSON_String;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "null";
        }
        public BackgroundTask()
        {
            super();
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("aaaaaa",result);
            //Toast.makeText(ViewActivity.this,result,Toast.LENGTH_LONG).show();


            try
            {
                 JSON_String=result;
                  fun();
                //if our json converter function throws error means no internet available
            }
            catch (Exception e)
            {
                 Toast.makeText(ViewActivity.this,"NO INTERNET AVAILABLE",Toast.LENGTH_LONG).show();
//                //if Internet connection not available
//                snackbar.show();
//                dialog.dismiss();
            }
            dialog.dismiss();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

}