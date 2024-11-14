package com.hooniegit.Xtream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hooniegit.Xtream.Modules.Data.Data;
import com.hooniegit.Xtream.Modules.Data.Type;
import com.hooniegit.Xtream.Modules.Serialization.ByteSerialization;
import com.hooniegit.Xtream.Modules.Serialization.Sample.SampleClass;
import com.hooniegit.Xtream.Modules.Stream.Processor;
import com.hooniegit.Xtream.Modules.Stream.Sample.One;
import com.hooniegit.Xtream.Modules.Stream.Sample.Two;

@SpringBootApplication
public class XtreamApplication {

	private static final MilliRecorder recorder = new MilliRecorder();

	public static void main(String[] args) {
		SpringApplication.run(XtreamApplication.class, args);

		workspace_data();
		workspace_stream();
		// workspace_serialize();
	}

	/**
	 * <Data Test>
	 */
	private static void workspace_data() {
		// Define Data
		Data data = new Data();

		// Add Data
		recorder.put();
		List<String> sList = new ArrayList<>();
		for (int i=1; i<=3000; i++) {
			sList.add("Hello, World!");
		}
		data.set(Type.ONE, sList);
		recorder.put();

		// Read Data & Check
		recorder.put();
		List<String> rList = data.get(Type.ONE);
		recorder.put();

		System.out.println("[Data Set Time Spent] : " + recorder.measure(0, 1, 1) + " ns, " + recorder.measure(0, 1, 1000000) + " ms");
		System.out.println("[Data Get Time Spent] : " + recorder.measure(2, 3, 1) + " ns, " + recorder.measure(2, 3, 1000000) + " ms");
		recorder.clear();
	}

	/**
	 * <Serialization Test>
	 */
	private static void workspace_serialize() {
		ByteSerialization serialization = new ByteSerialization();
		SampleClass sample = new SampleClass(new ArrayList<>(), "Hooniegit", "neivekim76@gmail.com");

		try {
			// Serialize Data
			byte[] sampleByte = serialization.serializeToBytes(sample, SampleClass.class);

			// De-Serialize Data & Check
			SampleClass refactored = serialization.<SampleClass>deserializeFromBytes(sampleByte);
			System.out.println(">>" + refactored.getName() + "<<");
			System.out.println(">>" + refactored.getEmail() + "<<");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * <Stream API Processing Test>
	 */
	private static void workspace_stream() {
		// Define Processor
		Processor processor = new Processor();
		// Create Sample List
		List<One> oneList = new ArrayList<>();
		for (int i=0; i<1000000; i++) {
			if (i%2 == 0) {
				oneList.add(new One("Hooniegit", 2));
			} else {
				oneList.add(new One("Dohoon Kim", 28));
			}
		}
		// Process List & Check
		recorder.put();
		List<Two> twoList = processor.processList(oneList);
		recorder.put();

		recorder.put();
		List<Two> twoListDefault = oneList.stream()
            .map(one -> new Two(one.getName(), one.getAge())) // One -> Two로 매핑
            .collect(Collectors.toList());
		recorder.put();

		recorder.put();
		List<Two> twoListParallel = oneList.parallelStream()
            .map(one -> new Two(one.getName(), one.getAge())) // One -> Two로 매핑
            .collect(Collectors.toList());
		recorder.put();



		System.out.println("[Stream Process Time Spent] : " + recorder.measure(0, 1, 1) + " ns, " + recorder.measure(0, 1, 1000000) + " ms");
		System.out.println("[Stream Time Spent] : " + recorder.measure(2, 3, 1) + " ns, " + recorder.measure(2, 3, 1000000) + " ms");
		System.out.println("[Parallel Stream Time Spent] : " + recorder.measure(4, 5, 1) + " ns, " + recorder.measure(4, 5, 1000000) + " ms");
		recorder.clear();

	}

}
