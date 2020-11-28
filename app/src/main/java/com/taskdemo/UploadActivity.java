package com.taskdemo;

import androidx.appcompat.app.AppCompatActivity;
import dmax.dialog.SpotsDialog;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.taskdemo.model.Topics;

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
import java.util.Iterator;

import static com.taskdemo.Constants.college;
import static com.taskdemo.Constants.company;
import static com.taskdemo.Constants.interviewType;
import static com.taskdemo.Constants.jobType;
import static com.taskdemo.Constants.topic;

public class UploadActivity extends AppCompatActivity {

    Spinner companies,colleges,interviewT,jobT,topicSP;

    String selectedCompany,selectedCollege,selectedInterview,selectedJob,selectedTopic;
    String qTitle,qContent;
    EditText qTitleET,qContentET;
    String JSON_String;
    SpotsDialog dialog;

    Button uploadBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        companies=findViewById(R.id.companySP);
        colleges=findViewById(R.id.collegeSP);
        interviewT=findViewById(R.id.interviewSP);
        jobT=findViewById(R.id.jobSP);

        topicSP=findViewById(R.id.topicSP);
        qTitleET=findViewById(R.id.titleET);
        qContentET=findViewById(R.id.contentET);
        uploadBTN=findViewById(R.id.submitBTN);

        dialog=new SpotsDialog(UploadActivity.this);
        selectedCompany="NO";
        selectedCollege="NO";
        selectedInterview="NO";
        selectedJob="NO";
        selectedTopic="NO";

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,company);
        companies.setAdapter(adapter);

        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,college);
        colleges.setAdapter(adapter1);

        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,interviewType);
        interviewT.setAdapter(adapter2);

        ArrayAdapter<String> adapter3=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,jobType);
        jobT.setAdapter(adapter3);

        ArrayAdapter<String> adapter4=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,topic);
        topicSP.setAdapter(adapter4);


        companies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCompany=company.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        topicSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedTopic=topic.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        colleges.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCollege=college.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        interviewT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedInterview=interviewType.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        jobT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedJob=jobType.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        uploadBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                qTitle=qTitleET.getText().toString().trim();

                qContent=qContentET.getText().toString().trim();

                if( (qTitle.equals("")) ||( qContent.equals("") ) || selectedCollege.equals("NO") || selectedCompany.equals("NO") || selectedInterview.equals("NO") ||selectedJob.equals("NO") || selectedTopic.equals("NO"))
                {
                    Toast.makeText(UploadActivity.this,"Please fill all the choices",Toast.LENGTH_LONG).show();
                    return;
                }
                dialog.show();
                new UploadActivity.BackgroundTask().execute();





            }
        });



    }


     class BackgroundTask extends AsyncTask<Void,Void,String>
    {
        String json_url="https://vlearndroidrun.000webhostapp.com/uploadDemo.php";


        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url=new URL(json_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data= URLEncoder.encode("Title","UTF-8")+"="+URLEncoder.encode(qTitle,"UTF-8")+"&"+
                        URLEncoder.encode("Content", "UTF-8") + "=" + URLEncoder.encode(qContent, "UTF-8")+"&"+
                        URLEncoder.encode("Job", "UTF-8") + "=" + URLEncoder.encode(selectedJob, "UTF-8")+"&"+
                        URLEncoder.encode("Company", "UTF-8") + "=" + URLEncoder.encode(selectedCompany, "UTF-8")+"&"+
                        URLEncoder.encode("Interview", "UTF-8") + "=" + URLEncoder.encode(selectedInterview, "UTF-8")+"&"+
                        URLEncoder.encode("College", "UTF-8") + "=" + URLEncoder.encode(selectedCollege, "UTF-8")+"&"+
                        URLEncoder.encode("Topic", "UTF-8") + "=" + URLEncoder.encode(selectedTopic, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();

                InputStream inputStream=httpURLConnection.getInputStream();

               inputStream.close();
                httpURLConnection.disconnect();
                JSON_String="null";
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
            //Toast.makeText(UploadActivity.this,result,Toast.LENGTH_LONG).show();


            try
            {
               // JSON_String=result;
                //if our json converter function throws error means no internet available
                Toast.makeText(UploadActivity.this,"uploaded",Toast.LENGTH_SHORT).show();

            }
            catch (Exception e)
            {
                Toast.makeText(UploadActivity.this,"no internet",Toast.LENGTH_SHORT).show();
                //if Internet connection not available

            }
            dialog.dismiss();
            finish();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }


}