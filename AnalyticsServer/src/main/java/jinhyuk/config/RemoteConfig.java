package jinhyuk.config;

import com.jcraft.jsch.JSch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoteConfig {

    @Bean
    JSch jSch() {
        return new JSch();
    }

}
