package map;

import java.util.List;
import java.util.Map;

public interface MapService {

	public List<Map<String,Object>> map(double longitude,double latitude,int id,List<Object> params);
	public int getcount();
}
