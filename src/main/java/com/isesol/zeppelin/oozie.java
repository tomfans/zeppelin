package com.isesol.zeppelin;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.oozie.client.OozieClient;
import org.apache.oozie.client.WorkflowJob.Status;
import org.json.JSONObject;

public class oozie {

	public static void main(String[] args) throws IOException{
		
		StringBuilder result = new StringBuilder();
		DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("d:\\aaa.json")));
		String s = null;
		while ((s = in.readLine())!=null) {
			result.append(s);
		}
		
		JSONObject obj = new JSONObject(result.toString());  
		JSONObject obj2 = new JSONObject( obj.getString("ipx_bigData_cmd-isesol"));
		
		OozieClient wc = new OozieClient("http://10.215.4.164:11000/oozie"); 
        Properties conf = wc.createConfiguration(); 
        conf.setProperty("nameNode", "hdfs://nameservice1"); 
        conf.setProperty("oozie.wf.application.path", "hdfs://nameservice1/user/hue/oozie/workspaces/hue-oozie-1496904130.34"); 
        conf.setProperty("jobTracker", "yarnRM"); 
        conf.setProperty("mapreduce.job.user.name", "hdfs");
        conf.setProperty("user.name", "hdfs");
        //conf.setProperty("hue-id-w", "496");
        conf.setProperty("oozie.use.system.libpath", "true");
        conf.setProperty("dryrun", "false");
        conf.setProperty("fileName", "/high_frequency_files/highFrequencyCollect/collect/98566919-bedc-49c2-b489-986710a4c663/98566919-bedc-49c2-b489-986710a4c663_20170609141936_1.gz");
        conf.setProperty("appId", "highFrequencyCollect");
        conf.setProperty("machine_tool", "lopman-9999");
        conf.setProperty("bizId", "98566919-bedc-49c2-b489-986710a4c663");
        conf.setProperty("bizData", result.toString());
        try { 
            String jobId = wc.run(conf); 
            System.out.println(jobId);

            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
