package com.example.android.quakereport.util;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.android.quakereport.EarthquakeInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by gbans6 on 12/11/2016.
 */

public class QueryUtils {


    private static final String LOG_TAG=QueryUtils.class.getSimpleName();

    public QueryUtils() {
    }

    public static ArrayList<EarthquakeInfo> extractEarthquakeInfos(String serverUrl){
        ArrayList<EarthquakeInfo> earthquakeInfos = new ArrayList<>();

        try {
            URL url = createURL(serverUrl);
            String json = getServerResponse(url);

            JSONObject object = new JSONObject(json);
            JSONArray features = object.getJSONArray("features");

            for(int i =0 ; i< features.length() ; i++) {
                JSONObject info = features.getJSONObject(i);
                JSONObject properties = info.getJSONObject("properties");
                Double magnitude = properties.getDouble("mag");
                String place = properties.getString("place");
                Long time = properties.getLong("time");
                String quakeUrl = properties.getString("url");
                earthquakeInfos.add(new EarthquakeInfo( place, magnitude,time, quakeUrl));
            }

        } catch (JSONException ex) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", ex);
        } catch (IOException ex) {
            Log.e("QueryUtils", "Problem reading JSON results from server", ex);
        }

        return earthquakeInfos;
    }

    private static String getServerResponse(URL url) throws IOException {
        String response= "";

        if(url == null) {
            return response;
        }

        HttpURLConnection connection = null;
        InputStream inputStream = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setRequestMethod("GET");
            connection.connect();

            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                inputStream =connection.getInputStream();
                response = readFromStream(inputStream);
            }


        }catch (Exception ex) {
            Log.e(LOG_TAG,": Server connection failed:" + ex);
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
            if(inputStream != null) {
                inputStream.close();
            }
        }
        return response;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {

        if(inputStream == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        InputStreamReader reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader buffer = new BufferedReader(reader);
        String line = buffer.readLine();
        while(line != null) {
            builder.append(line);
            line = buffer.readLine();
        }

        return builder.toString();
    }

    private static URL createURL(String stringURL){
        URL url = null;
        try {
            url = new URL(stringURL);
            return url;
        }catch (Exception ex) {
            Log.e(LOG_TAG,": Invalid server URL :" + stringURL);
        }
        return url;
    }
}
