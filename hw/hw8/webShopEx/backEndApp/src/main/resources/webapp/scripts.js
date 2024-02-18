document.addEventListener("DOMContentLoaded", function() {
    fetchProducts();
});

function fetchProducts() {
    fetch('/api/products')
        .then(response => response.json())
        .then(products => displayProducts(products))
        .catch(error => console.error('Ошибка при получении списка товаров:', error));
}

function displayProducts(products) {
    const productList = document.getElementById("product-list");
    productList.innerHTML = "";

    products.forEach(product => {
        const li = document.createElement("li");
        li.textContent = `${product.name}: ${product.description} - $${product.price}`;

        const button = document.createElement("button");
        button.textContent = "Добавить в корзину";
        button.addEventListener("click", () => addToCart(product.id));
        li.appendChild(button);

        productList.appendChild(li);
    });
}

function addToCart(productId) {
    fetch(`/api/products/addToCart/${productId}`, { method: 'POST' })
        .then(response => {
            if (!response.ok) {
                throw new Error('Ошибка при добавлении товара в корзину');
            }
            alert('Товар добавлен в корзину!');
        })
        .catch(error => console.error('Ошибка при добавлении товара в корзину:', error));
}
