let products = [
    {
        name: "Iphone 13 Pro Max", // Tên sản phẩm
        price: 30000000, // Giá mỗi sản phẩm
        brand: "Apple", // Thương hiệu
        count: 2, // Số lượng sản phẩm trong giỏ hàng
    },
    {
        name: "Samsung Galaxy Z Fold3",
        price: 41000000,
        brand: "Samsung",
        count: 1,
    },
    {
        name: "IPhone 11",
        price: 15500000,
        brand: "Apple",
        count: 1,
    },
    {
        name: "OPPO Find X3 Pro",
        price: 19500000,
        brand: "OPPO",
        count: 3,
    },
]
// Hiển thị tất cả sản phẩm
function displayInfo(arr){
    arr.forEach(product=>{
        console.log(`${product.name}-${product.price}-${product.brand}-${product.count}`)     
    })
}

// 6. Thêm 1 sản phẩm bất kỳ vào trong mảng product
function addProduct(arr,name,price,brand,count){
    return arr.push({name,price,brand,count})
}
addProduct(products,"Ipad Air 4",15000000,"Apple",3)
displayInfo(products)
// 7. Xóa tất cả sản phẩm của thương hiệu "Samsung" trong giỏ hàng
function DeleteNameBrand(arr,name){
    let newArr=[]
    newArr.push(arr.filter(product=>product.brand.toLowerCase()!=name.toLowerCase()))
    return newArr
}
console.log(DeleteNameBrand(products,"Samsung"))
// 8. Sắp xếp giỏ hàng theo price tăng dần
function sortPrice(arr){
    return arr.sort((a, b) => a.price - b.price)
}
console.log(sortPrice(products))
// 9. Sắp xếp giỏ hàng theo count giảm dần
function sortCount(arr){
    return arr.sort((a, b) => b.count - a.count)
}
console.log(sortCount(products))
// 10. Lấy ra 2 sản phẩm bất kỳ trong giỏ hàng
function display2Info(arr){
    let newarr =[]
    for (let i = 0; i < 2; i++) {
        index= Math.round(Math.random(0,arr.length))
        newarr.push(arr[index])
    }
    return newarr
}
console.log(display2Info(products))