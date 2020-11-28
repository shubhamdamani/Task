package com.taskdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

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

public class QuestionDetail extends AppCompatActivity {

    TextView qt,qc;
    Integer num;
    String qid,upv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);
        qt=findViewById(R.id.Utitle);
        qc=findViewById(R.id.Ucontent);


        Intent intent=getIntent();
        String s=intent.getStringExtra("title");
        String s1=intent.getStringExtra("content");
        qid=intent.getStringExtra("qid");
        upv=intent.getStringExtra("up");

        int n=Integer.parseInt(upv);
        n++;
        upv= String.valueOf(n);
       // upv="5";
       // num=intent.getIntExtra("upv");
        qt.setText(s);
        qc.setText(s1);

        new QuestionDetail.BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void,Void,String>
    {
        String json_url="https://vlearndroidrun.000webhostapp.com/seenDemo.php";


        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url=new URL(json_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data= URLEncoder.encode("qid","UTF-8")+"="+URLEncoder.encode(qid,"UTF-8")+"&"+
                        URLEncoder.encode("up", "UTF-8") + "=" + URLEncoder.encode(upv, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();

                InputStream inputStream=httpURLConnection.getInputStream();
//                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
//                StringBuilder stringBuilder=new StringBuilder();
//
//                while((json_string=bufferedReader.readLine())!=null)
//                {
//                    stringBuilder.append(json_string+"\n");
//                }
//
//                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
//                JSON_String=stringBuilder.toString().trim();
                return "null";


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
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

            try
            {
//                JSON_String=result;
//                fun();
                //if our json converter function throws error means no internet available
            }
            catch (Exception e)
            {
//                Snackbar snackbar=Snackbar.make(getActivity().findViewById(R.id.drawer_layout),"No Internet Connection",Snackbar.LENGTH_LONG);
//                //if Internet connection not available
//                snackbar.show();
//                dialog.dismiss();
            }

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}