package sell.crotroller;


import sell.VO.ProductInfoVo;
import sell.VO.ProductVo;
import sell.VO.ResultVO;
import sell.dataobject.ProductCategory;
import sell.dataobject.ProductInfo;
import sell.service.CategoryService;
import sell.service.ProductService;
import sell.util.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public ResultVO list(){
        //查询所有上架商品
        List<ProductInfo> productInfoList=productService.findUpAll();
        //查询类目(一次性查询)
//        List<Integer> categotyTypeList=new ArrayList<>();
//        for(ProductInfo productInfo:productInfoList){
//            categotyTypeList.add(productInfo.getCategoryType());
//        }
        List<Integer>categotyTypeList= productInfoList.stream()
                .map(e->e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory>productCategoryList=categoryService.findByCategoryTypeIn(categotyTypeList);

        //数据拼装
        List<ProductVo>productVoList=new ArrayList<>();
        for(ProductCategory productCategory:productCategoryList){
            ProductVo productVo=new ProductVo();
            productVo.setCategoryType(productCategory.getCategoryType());
            productVo.setCategoryName(productCategory.getCategoryName());;


            List<ProductInfoVo>productInfoVoList=new ArrayList<>();
            for(ProductInfo productInfo:productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVo productInfoVo=new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVOList(productInfoVoList);
            productVoList.add(productVo);

        }


        return ResultVOUtil.success(productVoList);
    }
}
