package personal_use.janie_product.repository

import org.springframework.data.jpa.repository.JpaRepository
import personal_use.janie_product.model.Product

interface ProductRepository : JpaRepository<Product,Long>
