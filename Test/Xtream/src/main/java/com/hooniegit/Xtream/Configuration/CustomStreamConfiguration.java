package com.hooniegit.Xtream.Configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hooniegit.Xtream.Tools.Handler;
import com.hooniegit.Xtream.Tools.StreamAutoConfiguration;
import com.hooniegit.Xtream.Configuration.DataClass.Sample;
import com.hooniegit.Xtream.Configuration.Handlers.Handler01;
import com.hooniegit.Xtream.Configuration.Handlers.Handler02;

@Configuration
public class CustomStreamConfiguration {

    @Bean
    public List<Handler<Sample>> handlers() {
        return List.of(new Handler01(), new Handler02());
    }

    @Bean
    public StreamAutoConfiguration<Sample> streamAutoConfiguration() {
        return new StreamAutoConfiguration<>();
    }

}

