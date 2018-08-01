package com.idhayangal.registry.common;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.logging.*;


import com.fasterxml.jackson.databind.ObjectMapper;

public class Helper {

	static final Logger LOG = Logger.getLogger(Helper.class.getName());
	public static String generate_id(String szPrefix)
	{
		return szPrefix + UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static String response(String message, int code)
	{
		String szResponse = String.format("{\"message\": \"%s\", \"code\": %d}", message, code);
				
		return szResponse;
	}

	public static Map processMergedFields(Map input, Map flow_data) throws Exception {
		Pattern pattern = Pattern.compile("\\{\\{(.*?)\\}\\}");
		Map retInput = new LinkedHashMap();
		for (Object key: input.keySet())
		{
			try
			{
				LOG.fine("DEBUG: Input key : " + (String)key);
				LOG.fine("DEBUG: Input value : " + (String)input.get((String)key));
	
				Matcher matcher = pattern.matcher((String)input.get((String)key));
				Map data = new HashMap(flow_data);
				ObjectMapper mapper = new ObjectMapper();
				
				LOG.fine("DEBUG: Data value : " + mapper.writeValueAsString(data));
				List index_keys = new ArrayList<String>();
				if (matcher.find()) {
					String index_key = matcher.group();
					index_key = index_key.substring(2, index_key.length() - 2);
					LOG.fine("DEBUG: Parser : index_key : " + index_key);
					String [] keys = index_key.split("\\.");
					LOG.fine("DEBUG: Parser : length of keys : " + keys.length);
					for (String key1 : keys)
					{
						LOG.fine("DEBUG: Parser : key1 : " + key1);
	
						if (key1.compareToIgnoreCase("flow") != 0)
							index_keys.add(key1);
					}
					
					
					LOG.fine("DEBUG: Parser : size of index_keys : " + index_keys.size());
	
					for (int i = 0; i < index_keys.size() - 1; i++)
					{
						LOG.fine("DEBUG: Parser : parsing index_key : " + index_keys.get(i));
		
						if (data.get(((String) index_keys.get(i))) != null)
						{
							LOG.fine("DEBUG: Parser : parsing index_key : is valid ");
							LOG.fine("DEBUG: Parsing Data value : " + mapper.writeValueAsString(data));
		
							data = (Map) data.get(((String) index_keys.get(i)));
							LOG.fine("DEBUG: Parsed Data value : " + mapper.writeValueAsString(data));
						}
						else
						{
							LOG.fine("DEBUG: Parser : parsing index_key : is not valid ");
		
							data = null;
							break;
						}
					}
					if (index_keys.size() == 0)
						data = null;
					Object value = (Object) input.get(key);
					if (data != null)
					{
						LOG.fine("DEBUG: Parser : parsing : is valid ");
						LOG.fine("DEBUG: Parser Data value : " + mapper.writeValueAsString(data));
		
						String last_key = (String) index_keys.get(index_keys.size() - 1);
						//System.out.println("DEBUG: Parser : parsing last key :  " + last_key + " ::: " + last_key.substring(2, last_key.length() - 2) );
						LOG.fine("DEBUG: Parser : parsing last key :  " + last_key );
		
						if ((Object) data.get(last_key) == null)
						{
							LOG.fine("DEBUG: Parser : .. uhm.. why is last key value is :  " + value);
							if (last_key.endsWith("]")) //the key is an array index .. e.g. INVOICE[0]
							{
								LOG.fine("DEBUG : XXXXX : last_key ends with ]");
								int nPos = last_key.indexOf("[");
								if (nPos != -1)
								{
									LOG.fine("DEBUG : XXXXX : last_key has [");
									
									LOG.fine("DEBUG : XXXXX : last_key  data " + last_key + " " + nPos + " " + last_key.length());

									LOG.fine("DEBUG : XXXXX : last_key request for index element " + last_key.substring(nPos + 1, last_key.length() - 1));
									int nIndex = Integer.valueOf(last_key.substring(nPos + 1, last_key.length() - 1));

									List lValue = (List) data.get(last_key.substring(0, nPos));
									value = lValue.get(nIndex);
									LOG.fine("DEBUG: XXXXXX : last_key found value to be " + value);
									retInput.put((String) key, value);
								}
								else
									retInput.put((String) key, input.get(key));
							}
							else
								retInput.put((String) key, input.get(key));
						}
						else
						{
							retInput.put((String) key, data.get(last_key));
						}
								
					}
					else
					{
						retInput.put((String) key, input.get(key));
					}
				}
				else
					retInput.put((String) key, input.get(key));
			}
			catch (Exception e)
			{
				retInput.put((String) key, input.get(key));
			}
		}
		
		return retInput;
	}
	
	public static void postJsonToRemoteTarget(String json, String targetUrl) throws Exception {
	    URL url = new URL(targetUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");

        OutputStream os = conn.getOutputStream();
        try {
            os.write(json.getBytes("UTF-8"));
        } finally {
            os.close();
        }
        
        InputStream in = conn.getInputStream();
        try {
            StringBuilder sb = new StringBuilder();
            byte[] buff = new byte[2048];
            int n;
            while ((n = in.read(buff)) != -1) {
                sb.append(new String(buff, 0, n));
            }
            LOG.fine(sb.toString());
        } finally {
            in.close();
        }
        conn.disconnect();
	}
	
	private static Map LinkedHashMap() {
		// TODO Auto-generated method stub
		return null;
	}
}
