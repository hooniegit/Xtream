package com.hooniegit.Xtream.Stream.Configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hooniegit.Xtream.Stream.Handler;
import com.hooniegit.Xtream.Stream.Stream;
import com.hooniegit.Xtream.Stream.StreamBuilder;
import com.hooniegit.Xtream.Stream.StreamManager;
import com.hooniegit.Xtream.Stream.Handlers.Event01Handler;
import com.hooniegit.Xtream.Stream.Handlers.Event02Handler;

/**
 * LMAX Stream Configuration On Spring
 */
@Configuration
public class StreamConfiguration {
	
    @Value("${LMAX.stream.size}") // Defined At Application.yml
    private int streamSize;
	
    /**
     * Create Stream Bean
     * @return
     */
    @Bean
    public StreamBuilder streamBuilder() {
    	// ** NEED TO DEFINE YOUR OWN HANDLER & ADD **
        Handler handler1 = new Event01Handler();
        Handler handler2 = new Event02Handler();
        
        return new StreamBuilder(List.of(handler1, handler2));
    }

    /**
     * Create List<Stream> Bean
     * @param streamBuilder
     * @return
     */
    @Bean
    public List<Stream> streamList(StreamBuilder streamBuilder) {
        List<Stream> streamList = new ArrayList<>();
        for (int i = 0; i < streamSize; i++) {
            streamList.add(streamBuilder.build());
        }
        
        return streamList;
    }

    /**
     * Create StreamManager Bean
     * @param streamList
     * @return
     */
    @Bean
    public StreamManager streamManager(List<Stream> streamList) {
        return new StreamManager(streamList);
    }
}
