package p;

import java.util.Map;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.*;

@ApplicationPath("/apimadang")
public class MadnagApplication extends Application{
	public Map<String,Object> getProperties(){
		Map<String, Object> rtn = new HashMap<>();
		
		rtn.put("jersey.config.server.provider.package","p");
		
		return rtn;
	}
}
