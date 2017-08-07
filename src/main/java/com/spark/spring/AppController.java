package com.spark.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.spring.domain.objects.Pizza;


@Controller
public class AppController {

	@RequestMapping(value="/pizzavalley/{pizzaName}", method = RequestMethod.GET)
	public String getPizza(@PathVariable String pizzaName, ModelMap model) {
 
		Pizza pizza = new Pizza(pizzaName);
		model.addAttribute("pizza", pizza);
 
		return "pizza";
 
	}
	
	 @RequestMapping(value = "/pizza", method = RequestMethod.GET)
	  @ResponseBody
	  public Pizza echo(@RequestBody final Pizza pizza)
	  {
	    return pizza;
	  }
}
//D:\wrkspc\wrkspc_sprVali\cnvr it is running 
// https://spring.io/blog/2009/03/16/adding-an-atom-view-to-an-application-using-springs-rest-support/
//http://websystique.com/springmvc/spring-4-mvc-contentnegotiatingviewresolver-example/
