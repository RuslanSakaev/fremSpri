// скрипт для загрузки списка товаров с сервера и отображения на странице
document.addEventListener("DOMContentLoaded", function() {
    fetchProducts();
});

function fetchProducts() {
    // Здесь будет код для отправки запроса на сервер и получения списка товаров
    // После получения списка, вызовем функцию displayProducts для отображения на странице
    const products = [
        { id: 1, name: "Product 1", price: 10 },
        { id: 2, name: "Product 2", price: 20 },
        { id: 3, name: "Product 3", price: 30 }
    ];
    displayProducts(products);
}

function displayProducts(products) {
    const productList = document.getElementById("product-list");
    productList.innerHTML = ""; // Очистим список перед добавлением новых товаров

    products.forEach(product => {
        const productItem = document.createElement("div");
        productItem.classList.add("product-item");
        productItem.innerHTML = `
            <h3>${product.name}</h3>
            <p>Price: $${product.price}</p>
            <button>Add to Cart</button>
        `;
        productList.appendChild(productItem);
    });
}
