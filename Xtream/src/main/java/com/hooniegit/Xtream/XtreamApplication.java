package com.hooniegit.Xtream;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hooniegit.Xtream.Modules.Serialization.ByteSerialization;
import com.hooniegit.Xtream.Modules.Stream.Processor;
import com.hooniegit.Xtream.Modules.Stream.Sample.One;
import com.hooniegit.Xtream.Modules.Stream.Sample.SampleClass;
import com.hooniegit.Xtream.Modules.Stream.Sample.Two;

@SpringBootApplication
public class XtreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(XtreamApplication.class, args);

		workspace_stream();
		workspace_serialize();
	}

	private static void workspace_serialize() {
		ByteSerialization serialization = new ByteSerialization();
		SampleClass sample = new SampleClass("Hooniegit", "neivekim76@gmail.com");
		One sampleOne = new One("Hooniegit", 29);

		try {
			byte[] sampleByte = serialization.serializeToBytes(sample, SampleClass.class);
			SampleClass refactored = serialization.<SampleClass>deserializeFromBytes(sampleByte, SampleClass.class);
			System.out.println(">>" + refactored.getName() + "<<");
			System.out.println(">>" + refactored.getEmail() + "<<");

			byte[] sampleByteOne = serialization.serializeToBytes(sampleOne, One.class);
			One refactoredOne = serialization.<One>deserializeFromBytes(sampleByteOne, One.class);
			System.out.println(">>" + refactoredOne.getName() + "<<");
			System.out.println(">>" + refactoredOne.getAge() + "<<");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
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
