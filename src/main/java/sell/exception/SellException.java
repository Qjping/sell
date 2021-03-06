package sell.exception;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import sell.dataobject.mapper.ProductCategoryMapper;
import sell.enmu.ResultEnum;
@Getter
public class SellException extends  RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }
    public SellException(Integer code,String msg){
        super(msg);
        this.code=code;

    }

}
