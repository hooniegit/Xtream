package com.hooniegit.Xtream.TEST;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hooniegit.Xtream.MilliRecorder;
import com.hooniegit.Xtream.Modules.Data.Data;
import com.hooniegit.Xtream.Modules.Data.Type;
import com.hooniegit.Xtream.Modules.Serialization.ByteSerialization;
import com.hooniegit.Xtream.Modules.Serialization.Sample.SampleClass;

import jakarta.annotation.PostConstruct;

@Service
public class TestService {

	// Time Recorder
	private static final MilliRecorder recorder = new MilliRecorder();

	/**
	 * Workspace Service
	 */
    @PostConstruct
    public void listen() {
		dataTest();
		// serializeTest();
    }

	/**
	 * <Data Casting Test>
	 */
	private void dataTest() {
		// Define Data
		Data data = new Data();

		// Add Data
		recorder.put();
		List<String> sList = new ArrayList<>();
		for (int i=1; i<=1000; i++) {
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
	private void serializeTest() {
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

}

