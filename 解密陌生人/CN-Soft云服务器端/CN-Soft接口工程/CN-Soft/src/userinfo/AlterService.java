package userinfo;

import java.util.List;
import java.util.Map;

/**
 * Created by bonsen on 15/5/29.
 */
public interface AlterService {
    public boolean alter(int id, String nickname,String sex,String region,String sign,
                                          List<Object> params1,List<Object> params2,List<Object> params3,List<Object> params4);
}
