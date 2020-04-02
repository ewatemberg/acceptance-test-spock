package com.example.springboot.config


import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Configuration
import spock.mock.DetachedMockFactory

@Configuration
@EnableCaching
class IntegrationTestMockingConfig {

    def mockFactory = new DetachedMockFactory()

    //Si tuviese que mockear un llamado rest
//    @Bean
//    RestClient personaRestClient() {
//        mockFactory.Mock(PersonaRestClient)
//    }

}