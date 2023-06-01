package luuthuyvi.demo.services;


import luuthuyvi.demo.entity.Category;
import luuthuyvi.demo.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public void addCategory(Category category){
        categoryRepository.save(category);
    };

    public Category getCategoryById(long id){
        return categoryRepository.findById(id).orElse(null);
    }

    public void deleteCategory(long id){
        categoryRepository.deleteById(id);
    }

}
