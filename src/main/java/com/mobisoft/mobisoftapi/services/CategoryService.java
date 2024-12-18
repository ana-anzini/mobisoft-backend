package com.mobisoft.mobisoftapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobisoft.mobisoftapi.configs.exceptions.CategoryNotFoundException;
import com.mobisoft.mobisoftapi.dtos.category.CategoryDTO;
import com.mobisoft.mobisoftapi.models.Category;
import com.mobisoft.mobisoftapi.models.UserGroup;
import com.mobisoft.mobisoftapi.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserService userService;

	public Category findById(Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		return category.orElseThrow();
	}

	public Category create(CategoryDTO categoryDTO) {
		UserGroup userGroup = userService.getLoggedUser().getGroup();
		Category category = new Category();
		category.setDescription(categoryDTO.getDescription());
		category.setUserGroup(userGroup);
		category.setCode(categoryDTO.getCode());

		return categoryRepository.save(category);
	}

	public Category update(Long id, CategoryDTO categoryDTO) {
		Category existingCategory = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException(id));

		existingCategory.setDescription(categoryDTO.getDescription());
		existingCategory.setCode(categoryDTO.getCode());
		return categoryRepository.save(existingCategory);
	}

	public void delete(Long id) {
		Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
		categoryRepository.delete(category);
	}

	public List<Category> findAll() {
		UserGroup userGroup = userService.getLoggedUser().getGroup();
		return categoryRepository.findByUserGroupId(userGroup.getId());
	}

	public void deleteCategories(List<Long> ids) {
		List<Category> categories = categoryRepository.findAllById(ids);
		categoryRepository.deleteAll(categories);
	}
	
	public Category findByCode(String code) {
		return categoryRepository.findByCode(code);
	}
}
