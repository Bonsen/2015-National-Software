package message;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by bonsen on 15/5/30.
 */
public interface MessageService {
    public List<Map<String,Object>> Message(int id,int fid ,BigInteger time, List<Object> params0,List<Object> params1);
}
