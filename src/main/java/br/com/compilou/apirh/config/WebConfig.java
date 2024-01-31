package br.com.compilou.apirh.config;

import br.com.compilou.apirh.serialization.converter.YamlJackson2HttpMesageConverter;
import br.com.compilou.apirh.util.MediaType;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final org.springframework.http.MediaType MEDIA_TYPE_APPLICATION_YML = org.springframework.http.MediaType.valueOf("application/x-yaml");
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlJackson2HttpMesageConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(org.springframework.http.MediaType.valueOf(MediaType.APPLICATION_JSON))
                .mediaType("json", org.springframework.http.MediaType.valueOf(MediaType.APPLICATION_JSON))
                .mediaType("xml", org.springframework.http.MediaType.valueOf(MediaType.APPLICATION_XML))
                .mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML);
    }
}