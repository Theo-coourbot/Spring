package com.tpAnnonce.annonce.Repository;

import com.tpAnnonce.annonce.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository  extends CrudRepository<Category,Integer> {
}
