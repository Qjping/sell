package sell.crotroller;

import com.lly835.bestpay.rest.type.Post;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sell.dataobject.ProductCategory;
import sell.exception.SellException;
import sell.form.CategoryForm;
import sell.service.CategoryService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/seller/category")
public class AdminCategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map){
//        PageRequest pageRequest = new PageRequest(page,size);
        List<ProductCategory> productCategories = categoryService.findAll();
        map.put("categoryList",productCategories);
        return new ModelAndView("/category/list");
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm, BindingResult bindingResult,
                                         Map<String, Object> map){
//        PageRequest pageRequest = new PageRequest(page,size);
        if(bindingResult.hasErrors()){
            map.put("url","/sell/seller/categoryList/list");
            return new ModelAndView("error/common");
        }
        ProductCategory productCategory = new ProductCategory();
        try{
            if(!StringUtils.isEmpty(productCategory.getCategoryId())){
                productCategory = categoryService.findOne(productCategory.getCategoryId());
            }
            BeanUtils.copyProperties(categoryForm,productCategory);
            categoryService.save(productCategory);
            map.put("url","success/common");
        }catch (SellException e){
            map.put("url","/sell/seller/categoryList/list");
            return new ModelAndView("error/common");
        }
        List<ProductCategory> productCategories = categoryService.findAll();
        map.put("categoryList",productCategories);
        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }
    @GetMapping("/index")
    public ModelAndView save(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                             Map<String, Object> map){
        if (categoryId != null) {
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("category", productCategory);
        }

        return new ModelAndView("category/index", map);

    }
}
