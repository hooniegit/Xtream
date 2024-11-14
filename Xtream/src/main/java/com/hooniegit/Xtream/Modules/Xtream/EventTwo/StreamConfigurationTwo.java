package com.hooniegit.Xtream.Modules.Xtream.EventTwo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hooniegit.Xtream.Modules.Xtream.Handler;
import com.hooniegit.Xtream.Modules.Xtream.StreamBuilder;
import com.hooniegit.Xtream.Modules.Xtream.StreamManager;
import com.hooniegit.Xtream.Modules.Xtream.Configuration.Stream;

/**
 * LMAX Stream Configuration On Spring
 */
@Configuration
public class StreamConfigurationTwo {
	
    @Value("${LMAX.stream.size}") // Defined At Application.yml
    private int streamSize;
	
    /**
     * Create Stream Bean
     * @return
     */
    @Bean
    public StreamBuilder streamBuilder() {
    	// ** NEED TO DEFINE YOUR OWN HANDLER & ADD **
        Handler handler1 = new HandlerTwo();
        
        return new StreamBuilder(List.of(handler1));
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
    @Bean(name = "StreamManagerTwo")
    public StreamManager streamManager(List<Stream> streamList) {
        return new StreamManager(streamList);
    }
}

