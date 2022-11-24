package personal_use.janie_product.Service
import org.springframework.stereotype.Service
import personal_use.janie_product.model.Product
import personal_use.janie_product.repository.ProductRepository

@Service
class ProductService (

    private val productRepository: ProductRepository
        ) {

    fun getAll(): List<Product> {
        val clothes = productRepository.findAll()
        return clothes
    }

    fun getByID(id: Long): Any {
        val findItem = productRepository.findById(id)
        // in this case out items are just objects
        // null means existing , no presence ( List )
        // isPresent means existing , presence (Object, table)
        // isEmpty means existing , no presence ( Object, table)
        if (findItem.isPresent) {
            return findItem
        } else {
            return "Item hasn't found"
//            return throw java.lang.RuntimeException("Clothes by id hasn't found")
        }
    }
    fun importClothes(
        categorize_new: String, brand_new: String? = null, quality_new: String, designer_new: String,
        price_new: Double, new_stock:Int,new_size:String
    ):
            Product {
        val newProduct = Product(
            categorize = categorize_new,
            brand = brand_new,
            quality = quality_new,
            designer = designer_new,
            price = price_new,
            stock = new_stock,
            size = new_size
            //total_price = new_total_price

        )

        val savedNewClothes = productRepository.save(newProduct)

        return savedNewClothes
    }

    fun clearOne_See_Remaining (id: Long): List<Product> {
        productRepository.deleteById(id)
        val remainingClosets = productRepository.findAll()
        remainingClosets.toMutableList()
        return remainingClosets

    }


    fun clearOne_Without_Seeing_Remaining (id: Long): String {

        // parameter is havin id: Long , becayse we will work with One , also we will bring use with codes down
        //there
        productRepository.deleteById(id)
        return "$id is has been donated"
    }

//    fun update_cloth (id: Long, categoryReq: CategoryReq) : Any {
//        // inside parenthesis ( arguments ) jg update ey klas dak ah ng jol
//        var closetOpt = closetRepository.findById(id)
//        if (closetOpt.isPresent) {
//            val closet = closetOpt.get()
//            closet.categorize = categoryReq.category
//            val saved = closetRepository.save(closet)
//            return saved
//        }
//        else{
//            return "This item doesn't exist"
//        }
//    }
    fun clearAll(): String {
    productRepository.deleteAll()
    return "Everything has been succesfully cleared."
}
    fun update_stock (id:Long, new_category:String,new_stock: Int): Product? {
        val cloth= productRepository.findById(id)
        if(cloth.isPresent){
            val closet = cloth.get()
            closet.categorize = new_category
            closet.stock = new_stock
            val saved = productRepository.save(closet)
            return saved

        }
        else{
            return null
        }
    }

}