
# 🛒  E-Commerce Backend

A Spring Boot-based REST API backend for an e-commerce application.  
Supports user authentication, cart management, order placement, and product listing.

---

## 🚀 Features Implemented

- ✅ User Registration and Login (JWT / Basic Auth)
- ✅ Product Model and Repository
- ✅ Add to Cart
- ✅ Checkout → Create Order with Order Items
- ✅ Auto-clear Cart after Checkout
- ✅ Delete Product
- ✅ Add Product
- ✅ Entity Relationships with JPA (`User`, `Product`, `Cart`, `Order`, `OrderItem`)

---

## 📁 Project Structure

```
src/main/java/com/example/test/
├── Controller/           → REST endpoints
├── Model/                → Entity classes
├── Service/              → Business logic
├── Repository/           → JPA Repositories
├── Filter/               → JWT Filter
├── Configuration/        → Security + App Config
└── Advice/Exception/     → Global exception handling
```

---

## ⚙️ Technologies Used

- **Java 24**, Spring Boot 3.5+
- Spring Security
- Spring Data JPA
- MySQL
- Hibernate
- Lombok
- JWT (via `jjwt`)
- IntelliJ IDEA

---

## 📌 Current API Endpoints (Highlights)

### 🧑‍💻 Authentication
| Method | Endpoint      | Description          |
|--------|---------------|----------------------|
| POST   | `/register`   | Register a new user  |
| POST   | `/login`      | Login and get token  |

### 🛒 Cart
| Method | Endpoint           | Description              |
|--------|--------------------|--------------------------|
| POST   | `/addToCart`       | Add product to cart      |
| POST   | `/checkout/{id}`   | Place order (from cart)  |

### 🛠️ Product (Admin)
| Method | Endpoint           | Description              |
|--------|--------------------|--------------------------|
| POST   | `/products`        | Add new product          |
| DELETE | `/products/{id}`   | Delete a product by ID   |

---

## 📦 Entities

- `Users` → Registered customer
- `Product` → Items to purchase
- `Cart` → User’s temporary holding area before buying
- `Orders` → Finalized purchases
- `OrderItem` → Product line items per order

---

## ✅ Next Feature Targets

- 📜 Order History by user
- 🛠 Admin-only product CRUD
- 🔍 Product search & filtering
- 📈 Analytics Dashboard
- 🧾 Order details endpoint

---

## 🏁 Running the App

Make sure MySQL is running, and database `test` is created.

```bash
mvn spring-boot:run
```

Application will start at:  
`http://localhost:8080/`

---

## 🧠 Author

KalaiSelvam M — *Backend Developer in Progress* 🚀

---
