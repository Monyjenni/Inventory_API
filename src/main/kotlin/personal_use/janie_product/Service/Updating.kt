package personal_use.janie_product.Service

data class Updating(
    val categorize: String,
    val stock: Int
)

data class UpdateInventoryAdd(
    val newStock: Int
)

data class UpdateInventoryRemove(
    val newStock1: Int
)

