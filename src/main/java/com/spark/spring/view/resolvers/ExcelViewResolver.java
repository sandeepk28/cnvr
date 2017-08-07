/**
 * 
 */
package com.spark.spring.view.resolvers;
 
import java.util.Locale;
 
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
 
import com.spark.spring.views.GenerateExcel;
 
/**
 * @author Sony
 *
 */
public class ExcelViewResolver implements ViewResolver {
 
    /* (non-Javadoc)
     * @see org.springframework.web.servlet.ViewResolver#resolveViewName(java.lang.String, java.util.Locale)
     */
    @Override
    public View resolveViewName(String viewname, Locale locale) throws Exception {
        GenerateExcel generateExcel = new GenerateExcel();
        return generateExcel;
    }
 
}