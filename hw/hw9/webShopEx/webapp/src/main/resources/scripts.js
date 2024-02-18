// script.js

// Функция window.onload гарантирует, что скрипт будет выполнен только после полной загрузки страницы
window.onload = async () => {
  // Вызываем функцию fetchProducts для загрузки списка продуктов при загрузке страницы
  fetchProducts();
};

async function fetchProducts() {
  const productList = document.getElementById('product-list');

  try {
    // Отправляем асинхронный запрос на сервер для получения списка продуктов
    const response = await fetch('/products');
    // Получаем данные о продуктах в формате JSON из ответа сервера
    const products = await response.json();

    // Очищаем список перед добавлением новых продуктов
    productList.innerHTML = '';

    // Перебираем каждый продукт в полученном списке
    products.forEach(product => {
      // Создаем новый элемент списка для каждого продукта
      const li = document.createElement('li');
      // Устанавливаем текст элемента списка в формате "Название: Описание - Цена"
      li.textContent = `${product.name}: ${product.description} - $${product.price}`;
      // Добавляем элемент списка в родительский элемент списка продуктов
      productList.appendChild(li);
    });
  } catch (error) {
    // В случае возникновения ошибки выводим сообщение об ошибке в консоль
    console.error('Error fetching products:', error);
  }
}
