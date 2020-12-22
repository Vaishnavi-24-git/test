package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.offbytwo.jenkins.*;
import com.offbytwo.jenkins.model.*;


@CrossOrigin(origins = "*")
@RestController
public class JenkinsSetupController {
	
	@Value("${application.base.path}")
	String urlPath;
	
	@Value("${ConfigXMLTemplate}")
	String xmlTemplate;
	
	@GetMapping("/getMessage")
	public final String getMessage() throws IOException {
     String values="Hi..Welcome";
 		return values;
	}
	
	@GetMapping("/buildJob")
	public String main(String[] args) {
	    try {
	      URL url = new URL(urlPath+"job/secondJob/build"); // Jenkins URL localhost:8080, job named 'test'
	      String user = "admin"; // username
	      String pass = "115d095dadd00b09dc4d59606c069bc395"; // password or API token
	      String authStr = user + ":" + pass;
	      String encoding = Base64.getEncoder().encodeToString(authStr.getBytes("utf-8"));

	      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	      connection.setRequestMethod("POST");
	      connection.setDoOutput(true);
	      connection.setRequestProperty("Authorization", "Basic " + encoding);
	      System.out.println(connection);

	      InputStream content = connection.getInputStream();
	      BufferedReader in = new BufferedReader(new InputStreamReader(content));
	      String line;
//	      System.out.println(connection.getResponseMessage());
	      while ((line = in.readLine()) != null) {
	        System.out.println(line);
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
		
		return null;
	  }
	
	@GetMapping("/testConnection/createItem")
	public void jenkins(@RequestParam String jobName ) throws IOException, InterruptedException
	{
       
		String url=urlPath +"/createItem?name=" +jobName;
		URL obj=new URL(url);
		String user = "admin"; // username
	    String pass = "115d095dadd00b09dc4d59606c069bc395"; // password or API token
	    String authStr = user + ":" + pass;
	    String encoding = Base64.getEncoder().encodeToString(authStr.getBytes("utf-8"));
		HttpURLConnection con=(HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
	    con.setDoOutput(true);
		con.setRequestProperty("Authorization", "Basic " + encoding);
		con.setRequestProperty("Content-Type","application/xml");
		Thread.sleep(10000);
		System.out.println("Job status is " +con.getResponseMessage());
		System.out.println("execution ended");
		con.disconnect();
	}
	
	@GetMapping("/createJob")
	 public void jenkinsMethod(@RequestParam String jobName)throws URISyntaxException, IOException
	{
		
        @SuppressWarnings("resource")
		JenkinsServer server = new JenkinsServer(new URI(urlPath), "admin", "115d095dadd00b09dc4d59606c069bc395");

        Map<String, Job> jobs = server.getJobs();
        for (Map.Entry<String, Job> entry : jobs.entrySet())
        {

            String name = entry.getKey();
            JobWithDetails jobdet = jobs.get(name).details();
            System.out.println("Job Links");
            System.out.println(jobdet.getUrl());
            System.out.println("Next Build Number");
            System.out.println(jobdet.getNextBuildNumber());
            System.out.println("Detail by build number");
            System.out.println(jobdet.getBuildByNumber(1));
            System.out.println(jobdet);

        }

	  server.createJob(jobName, xmlTemplate);
	}
	
	
	public JenkinsServer renameJob(String oldJobName, String newJobName) throws IOException {
        return renameJob(oldJobName, newJobName);       
    }
	public JenkinsServer createJob(String jobName, String jobXml) throws IOException {
	        return createJob( jobName, jobXml);
	}
	
}
