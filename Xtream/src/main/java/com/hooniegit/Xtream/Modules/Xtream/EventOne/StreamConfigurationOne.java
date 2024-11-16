package com.hooniegit.Xtream.Modules.Xtream.EventOne;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
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
public class StreamConfigurationOne {
	
    @Value("${Xtream.EventOne.size}") // Defined At Application.yml
    private int streamSize;
	
    /**
     * Create Stream Bean
     * @return
     */
    @Bean(name = "StreamBuilderOne")
    public StreamBuilder streamBuilder() {
    	// ** NEED TO DEFINE YOUR OWN HANDLER & ADD **
        Handler handler1 = new HandlerOne();
        
        return new StreamBuilder(List.of(handler1));
    }

    /**
     * Create List<Stream> Bean
     * @param streamBuilder
     * @return
     */
    @Bean(name = "StreamListOne")
    public List<Stream> streamList(@Qualifier("StreamBuilderOne") StreamBuilder streamBuilder) {
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
    @Bean(name = "StreamManagerOne")
    public StreamManager streamManager(@Qualifier("StreamListOne") List<Stream> streamList) {
        return new StreamManager(streamList);
    }
}
