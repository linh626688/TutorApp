//package helix.com.tutorapp;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.ByteArrayHttpMessageConverter;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class WebMvcConfig extends WebMvcConfigurerAdapter {
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        final ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
//        final List<MediaType> list = new ArrayList<MediaType>();
//        list.add(MediaType.IMAGE_JPEG);
//        list.add(MediaType.IMAGE_PNG);
//        list.add(MediaType.APPLICATION_OCTET_STREAM);
//        arrayHttpMessageConverter.setSupportedMediaTypes(list);
//        converters.add(arrayHttpMessageConverter);
//
//        super.configureMessageConverters(converters);
//    }
//
//}