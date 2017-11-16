/**
 *@author huangdongxu
 *@Date Nov 14, 2017
*/

package org.davingci.api;

import org.davingci.filter.CORSResponseFilter;
import org.glassfish.jersey.server.ResourceConfig;



public class Application extends ResourceConfig {
	
	 /**
		* Register JAX-RS application components.
		*/	
		public Application(){
			packages("org.davingci.api");
			register(CORSResponseFilter.class);
		}

}
