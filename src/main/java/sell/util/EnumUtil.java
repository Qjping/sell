package sell.util;

import org.aopalliance.reflect.Code;
import sell.enmu.CodeEnum;

public class EnumUtil {
    public static<T extends CodeEnum> T getByCode(Integer code,Class<T>enumClass){
        for(T each:enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }

        }
        return null;
    }
}
