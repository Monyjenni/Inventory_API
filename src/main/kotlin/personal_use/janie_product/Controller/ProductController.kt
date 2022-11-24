package personal_use.janie_product.Controller

import org.springframework.web.bind.annotation.*
import personal_use.janie_product.Service.*
import personal_use.janie_product.model.Product
import personal_use.janie_product.repository.ProductRepository
//import personal_use.janie_product.response.ProductWithPriceRes

@RestController
@ControllerAdvice
@RequestMapping("api/v1")

class ClosetController(

    private val closetService: ProductService,
    private val productRepository: ProductRepository

){
    @GetMapping("/getAll")
    fun getAll(): List<Product>{
        return closetService.getAll()
    }

    @GetMapping("/get/{id}")
    fun getById(@PathVariable("id") id:Long): Any{
        val getProduct = productRepository.findById(id)
        if(getProduct.isPresent){
            return closetService.getByID(id)
        }else{
           throw java.lang.RuntimeException("Product not found")
//            return "product not found"
        }
    }
    @PostMapping("/AddOne")
    fun createById (@RequestBody clothes : AddThroughController): Product {
        return closetService.importClothes(
            categorize_new = clothes.categorize,
            brand_new = clothes.brand,
            quality_new = clothes.quality,
            designer_new = clothes.designer,
            price_new = clothes.price,
            new_stock =clothes.stock,
            new_size = clothes.size )
            //new_total_price = clothes.total_price
    }

//   @PostMapping("/AddStock")
//   fun addStock (@RequestBody )


    @DeleteMapping("/clearOne/View_Remaining/{id}")
    fun deleteOne (@PathVariable("id")id: Long): List<Product>{
        return closetService.clearOne_See_Remaining(id)
    }

    @DeleteMapping("/clearOne/Without_View_Remaining/{id}")
    fun deleteTwo (@PathVariable("id")id: Long):String{
        return closetService.clearOne_Without_Seeing_Remaining (id)
    }

    @DeleteMapping("/clearAll")
    fun deleteAll ():String{
        return closetService.clearAll()
    }

//    @PutMapping("/change/put/{id}")
//    fun updateClo (@PathVariable id:Long , @RequestBody dto: Updating):Closet{
//        return closetService.update_cloth()
//    }

    @PutMapping("/update/{id}")
    fun  updateClo (@PathVariable id:Long , @RequestBody dto: Updating): Product {

        val updatedCloset = closetService.update_stock(id, new_category = dto.categorize , new_stock = dto.stock)
        if (updatedCloset != null) {
            return updatedCloset
        } else {
            // send the msg then exit the code
            throw java.lang.RuntimeException("No product found :)) ")
        }

//        return closetService.update_cloth2(id, new_category = dto.category ) ?: throw java.lang.RuntimeException("There is no clothest")

//      return closetService.update_cloth2(id, new_category = dto.category )
    }

    @PutMapping("/update/inventory/add/{id}")

    // request just var which is assigned of the UpdateInventoryAdd
    fun  addInventory (@PathVariable id:Long , @RequestBody request: UpdateInventoryAdd): Product{

// This way will update the id since we put the id into RequestBody
//    @PutMapping("/update/inventory/add")
//    fun  addInventory (@RequestBody request: UpdateInventory): Product {
        val item= productRepository.findById(id)

        if(item.isPresent){

            // get the value of the item besides the loop and assign the value into closet
            val vathuk = item.get()

            //add the new request with exist one
            val totalStockAdding = request.newStock + vathuk.stock

            //after multiplication , we need to take totalstock assigns into the old stock( closet.stock),
            // it's just the object in the application but not in the database
            vathuk.stock = totalStockAdding

//            val newTotalPrice = vathuk.stock * vathuk.price
//            vathuk.total_price = newTotalPrice


            // then save everything in database (to know that becoz productRepo )
            val saved = productRepository.save(vathuk)

            return saved

        }
        else{
            throw java.lang.RuntimeException("No product found")
        }


    }

    @PutMapping("/update/inventory/remove/{id}")
    fun removeInventory(@PathVariable id:Long , @RequestBody request1: UpdateInventoryRemove): Product{
        val item1 = productRepository.findById(id)
        if(item1.isPresent){

            //get() the value ??!
            val item2 = item1.get()

            val totalStockRemoving = item2.stock - request1.newStock1
            item2.stock=totalStockRemoving

            val saved = productRepository.save(item2)
            return saved
        }else {
            throw java.lang.RuntimeException("No product found")
        }
    }

}
data class AddThroughController (
    val categorize : String,
    val brand : String?,
    val quality : String,
    val designer : String,
    val price : Double,
    val stock : Int,
    val size : String,
   // val total_price : Double
        )