package personal_use.janie_product.model

import javax.persistence.*


@Entity
@Table(name= "closet")
data class Product (

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name= "Categorize")
    var categorize : String ,

    @Column(name="Brand")
    val brand : String?,

    @Column(name="Quality")
    val quality : String,

    @Column (name="Designer")
    val designer: String,

    @Column(name="Price")
    var price : Double = 0.0,

    @Column(name="Inventory")
    var stock : Int = 0,

    @Column(name="Size")
    var size : String ,

    @Column( name = "Total_Price")
    var total_price: Double = 0.0



)