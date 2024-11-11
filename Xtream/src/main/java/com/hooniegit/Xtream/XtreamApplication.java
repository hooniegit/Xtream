package com.hooniegit.Xtream;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hooniegit.Xtream.Modules.Serialization.ByteSerialization;
import com.hooniegit.Xtream.Modules.Stream.Processor;
import com.hooniegit.Xtream.Modules.Stream.Sample.One;
import com.hooniegit.Xtream.Modules.Stream.Sample.Two;

@SpringBootApplication
public class XtreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(XtreamApplication.class, args);
		// workspace_serialize();
		workspace_stream();
		workspace_serialize();
	}

	private static void workspace_serialize() {
		ByteSerialization serialization = new ByteSerialization();
		One sampleOne = new One("Hooniegit", Integer.valueOf(10));
		try {
			byte[] sampleByte = serialization.serializeToBytes(sampleOne, One.class);
			One refactored = serialization.deserializeFromBytes(sampleByte, One.class);
			System.out.println(refactored.getName() + ":" + refactored.getAge());
		} catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		}


	}

	private static void workspace_stream() {
		Processor processor = new Processor();
		List<One> oneList = new ArrayList<>();
		for (int i=0; i<100; i++) {
			if (i%2 == 0) {
				oneList.add(new One("Hooniegit", 2));
			} else {
				oneList.add(new One("Dohoon Kim", 28));
			}
		}

		List<Two> twoList = processor.processList(oneList);
		for (Two two : twoList) {
			System.out.println(two.getName() + ":" + two.getAge());
		}

	}

}
