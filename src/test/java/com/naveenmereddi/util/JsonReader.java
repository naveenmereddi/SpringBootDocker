package com.naveenmereddi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naveenmereddi.models.domain.Todo;

public class JsonReader {
	public static void main( String[] args )
	{
		ApplicationContext appContext =
				new ClassPathXmlApplicationContext();

		Resource resource =
				appContext.getResource("static/json/Todo.json");

		try{
			InputStream is = resource.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String line;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
			ObjectMapper mapper = new ObjectMapper();
			Todo todo = mapper.readValue(sb.toString(), Todo.class);
			System.out.println(todo.getTasks());

		}catch(IOException e){
			e.printStackTrace();
		}

	}

}
