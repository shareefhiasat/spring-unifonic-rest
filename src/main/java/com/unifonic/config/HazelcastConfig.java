package com.unifonic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;

@Configuration
public class HazelcastConfig {
    @Bean
    public Config hazelCastConfig() {
        return new Config().setInstanceName("hazelcast-instance")
            .addMapConfig(new MapConfig().setName("UnifonicCache")
                .setMaxSizeConfig(new MaxSizeConfig(500, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                .setEvictionPolicy(EvictionPolicy.LRU).setTimeToLiveSeconds(200));
    }
}
