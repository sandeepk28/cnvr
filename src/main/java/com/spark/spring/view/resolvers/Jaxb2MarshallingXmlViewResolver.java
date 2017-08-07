/**
 * 
 */
package com.spark.spring.view.resolvers;
 
import java.util.Locale;
 
import org.springframework.oxm.Marshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;
 
/**
 * @author Sony
 * 
 */
public class Jaxb2MarshallingXmlViewResolver implements ViewResolver {
 
    private Marshaller marshaller;
 
    public Jaxb2MarshallingXmlViewResolver(Marshaller marshaller) {
        this.marshaller = marshaller;
    }
 
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.ViewResolver#resolveViewName(java.lang
     * .String, java.util.Locale)
     */
    public View resolveViewName(String viewName, Locale locale)
            throws Exception {
        MarshallingView view = new MarshallingView();
        view.setMarshaller(marshaller);
        return view;
    }
 
}