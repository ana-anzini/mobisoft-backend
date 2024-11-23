package com.mobisoft.mobisoftapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobisoft.mobisoftapi.configs.exceptions.ProductProjectNotFoundException;
import com.mobisoft.mobisoftapi.dtos.productproject.ProductProjectDTO;
import com.mobisoft.mobisoftapi.models.Product;
import com.mobisoft.mobisoftapi.models.ProductProject;
import com.mobisoft.mobisoftapi.models.Project;
import com.mobisoft.mobisoftapi.repositories.ProductProjectRepository;

@Service
public class ProductProjectService {
	
	@Autowired
	private ProductProjectRepository productProjectRepository;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProductService productService;
	
	public ProductProject createProductProject(ProductProjectDTO productProjectDTO) {
		ProductProject productProject = new ProductProject();
		Project project = projectService.getProjectById(productProjectDTO.getProjectId());
		Product product = productService.getProductById(productProjectDTO.getProductId());
		
		productProject.setProject(project);
		productProject.setProduct(product);
		productProject.setProductValue(productProjectDTO.getProductValue());
		
		return productProjectRepository.save(productProject);
	}
	
	public ProductProject findById(Long id) {
		return productProjectRepository.findById(id).orElseThrow(() -> new ProductProjectNotFoundException(id));
	}
	
	public ProductProject updateProductProject(Long id, ProductProjectDTO productProjectDTO) {
		Project project = projectService.getProjectById(productProjectDTO.getProjectId());
		Product product = productService.getProductById(productProjectDTO.getProductId());
		ProductProject existingProductProject = productProjectRepository.findById(id)
				.orElseThrow(() -> new ProductProjectNotFoundException(id));

		existingProductProject.setProject(project);
		existingProductProject.setProduct(product);
		existingProductProject.setProductValue(productProjectDTO.getProductValue());
		
		return productProjectRepository.save(existingProductProject);
	}
	
	public void deleteProductProject(Long id) {
		ProductProject existingProductProject = productProjectRepository.findById(id)
				.orElseThrow(() -> new ProductProjectNotFoundException(id));
		productProjectRepository.delete(existingProductProject);
	}
	
	public void deleteProductProjects(List<Long> ids) {
		List<ProductProject> productProjects = productProjectRepository.findAllById(ids);
		productProjectRepository.deleteAll(productProjects);
	}
	
	public List<ProductProject> getProductsByProject(Long projectId) {
	    Project project = projectService.getProjectById(projectId);
	    return productProjectRepository.findByProject(project);
	}
}