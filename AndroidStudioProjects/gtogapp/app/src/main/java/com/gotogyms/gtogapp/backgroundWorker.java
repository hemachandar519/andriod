package com.gotogyms.gtogapp;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jyothsna on 7/2/2017.
 */

public class backgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    backgroundWorker(Context ctx){
        context=ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type=params[0];
        String login_url="http://192.168.56.1/login.php";
        String dbusername="";
        String dbuseremail="";
        if(type.equals("login")) {
            try {
                String user_name=params[1];
                String user_pass=params[2];
                URL url=new URL(login_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("user_pass","UTF-8")+"="+URLEncoder.encode(user_pass,"UTF-8")+"&"
                        +URLEncoder.encode("user_action","UTF-8")+"="+URLEncoder.encode("mgoogle","UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    result+=line;
                }

                JSONObject object = new JSONObject(result);

                dbusername= object.getString("username");
                dbuseremail= object.getString("mailid");

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return  dbusername;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        if (dbusername=="") {  return null; }
        else { return dbusername;  }
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    @Override
    protected void onPreExecute() {
        alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public void saveLoginDetails(){

    }
    public void clearLoginDetails(){

    }
}
