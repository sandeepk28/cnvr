package com.spark.spring.config;
 
import java.util.ArrayList;
import java.util.List;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
 
import com.spark.spring.domain.objects.Pizza;
import com.spark.spring.view.resolvers.ExcelViewResolver;
import com.spark.spring.view.resolvers.Jaxb2MarshallingXmlViewResolver;
import com.spark.spring.view.resolvers.JsonViewResolver;
import com.spark.spring.view.resolvers.PdfViewResolver;
 
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.spark.spring")
public class AppConfig extends WebMvcConfigurerAdapter {
 
    public void configureContentNegotiation(
            ContentNegotiationConfigurer contentNegotiationConfigurer) {
        contentNegotiationConfigurer.ignoreAcceptHeader(true)
                .defaultContentType(MediaType.TEXT_HTML);
    }
 
    @Bean
    public ViewResolver contentNegotiationViewResolver(
            ContentNegotiationManager contentNegotiationManager) {
        ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
        contentNegotiatingViewResolver
                .setContentNegotiationManager(contentNegotiationManager);
 
        List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
        resolvers.add(jaxb2MarshallingXmlViewResolver());
        resolvers.add(jsonViewResolver());
        resolvers.add(excelViewResolver());
      //  resolvers.add(pdfViewResolver());
 
        contentNegotiatingViewResolver.setViewResolvers(resolvers);
        return contentNegotiatingViewResolver;
    }
 
    /*
     * Configure View resolver to provide XML output Uses JAXB2 marshaller to
     * marshall/unmarshall POJO's (with JAXB annotations) to XML
     */
    @Bean
    public ViewResolver jaxb2MarshallingXmlViewResolver() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Pizza.class);
        return new Jaxb2MarshallingXmlViewResolver(marshaller);
    }
 
    /*
     * Configure View resolver to provide JSON output using JACKSON library to
     * convert object in JSON format.
     */
    @Bean
    public ViewResolver jsonViewResolver() {
        return new JsonViewResolver();
    }
     
    /*
     * Configure View resolver to provide PDF output using lowagie pdf library to
     * generate PDF output for an object content
     */
//    @Bean
//    public ViewResolver pdfViewResolver() {
//        return new PdfViewResolver();
//    }
 
    /*
     * Configure View resolver to provide XLS output using Apache POI library to
     * generate XLS output for an object content
     */
    @Bean
    public ViewResolver excelViewResolver() {
        return new ExcelViewResolver();
    }
     
    /*
     * Configure View resolver to provide HTML output This is the default format
     * in absence of any type suffix.
     */
    @Bean
    public ViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}