# 🚀 NexusMart E-Commerce Backend

**Last Updated:** July 20, 2025

A robust, full-featured **REST API backend** for the **NexusMart** e-commerce platform, built with **Spring Boot**. This backend offers comprehensive services for **user authentication**, **product catalog management**, **shopping cart operations**, **order processing**, and a brand-new **product review system** with sentiment analysis.

---

## ✨ Latest Feature: Product Review System

* **Submit Reviews:** Users can submit star ratings and text reviews for purchased products.
* **View Reviews:** Publicly viewable reviews per product.
* **Sentiment Analysis:** Python-based service categorizes reviews as *positive*, *negative*, or *neutral*.

---

## ✅ Features Overview

### 🛡️ Authentication & Authorization

* **JWT-based Authentication:** Secure login & stateless session management.
* **Role-Based Access Control:** User & Admin roles with protected endpoints using `@PreAuthorize`.

### 🛒 Product & Cart Management

* **Product Catalog (Admin):**

  * Add, update products with price & stock control.

* **Product Discovery:**

  * List all products.
  * Search products with partial match.

* **Advanced Shopping Cart:**

  * Add/view/update/remove items in cart.

### 📦 Order Management

* **Checkout Flow:**

  * Converts cart to order.
  * Automatically clears cart post-purchase.
* **Order History:**

  * Full order tracking per user.

### 📝 Product Reviews

* Submit & view product reviews.
* Reviews linked to user purchases and analyzed for sentiment.

### ⚠️ Robust Error Handling

* Global exception handling for:

  * Product not found
  * Out of stock
  * Invalid input, etc.

---

## 📁 Project Structure

```
src/main/java/com/example/test/
├── Controller/       # REST APIs
├── Model/            # JPA entities
├── Service/          # Business logic
├── Repository/       # Spring Data JPA
├── Filter/           # JWT authentication
├── Configuration/    # Security & App configs
└── Advice/Exception/ # Global exception handlers
```

---

## ⚙️ Tech Stack

| Technology        | Description                     |
| ----------------- | ------------------------------- |
| Java 21           | Core language                   |
| Spring Boot 3.5.3 | Backend framework               |
| Spring Security   | Authentication & Authorization  |
| Spring Data JPA   | ORM layer                       |
| Hibernate         | ORM provider                    |
| MySQL             | Relational database             |
| JWT (jjwt)        | Token-based authentication      |
| Lombok            | Boilerplate code reduction      |
| Maven             | Build and dependency management |

---

## 📌 API Endpoints

> **Base URL:** `http://localhost:8080`

### 🔐 Authentication

| Method | Endpoint    | Description                    |
| ------ | ----------- | ------------------------------ |
| POST   | `/Register` | Register a new user            |
| POST   | `/Login`    | Authenticate and get JWT token |

---

### 📦 Products (Public Access)

| Method | Endpoint                | Description                     |
| ------ | ----------------------- | ------------------------------- |
| GET    | `/getProduct`           | Retrieve all available products |
| GET    | `/Search/{productname}` | Search products by name         |

---

### 🛒 Cart Management

| Method | Endpoint                                      | Description                     |
| ------ | --------------------------------------------- | ------------------------------- |
| GET    | `/viewCart/{userid}`                          | View all items in user’s cart   |
| POST   | `/addToCart/{userid}/{productid}`             | Add product to cart             |
| POST   | `/updateCart/{userid}/{productid}/{quantity}` | Update product quantity in cart |
| POST   | `/removeCart/{userid}/{productid}`            | Remove product from cart        |

---

### 📥 Orders & Reviews

| Method | Endpoint                        | Description                           |
| ------ | ------------------------------- | ------------------------------------- |
| POST   | `/Checkout/{userid}`            | Place an order from cart              |
| GET    | `/orderDetails/{userid}`        | View user’s order history             |
| POST   | `/review/{productid}/{orderid}` | Submit a review for purchased product |
| GET    | `/review/{productid}`           | View all reviews for a product        |

---

### 🛠️ Admin Panel (Requires Admin Role)

| Method | Endpoint                                     | Description                   |
| ------ | -------------------------------------------- | ----------------------------- |
| POST   | `/addProducts`                               | Add a new product             |
| POST   | `/updateProducts/{productid}/{bool}/{price}` | Update stock status and price |

---

## 🗃️ Entity Overview

| Entity      | Description                                       |
| ----------- | ------------------------------------------------- |
| `Users`     | Stores user credentials and roles                 |
| `Product`   | Product details (name, price, stock, etc.)        |
| `Cart`      | Temporary storage for a user’s selected items     |
| `Orders`    | Finalized purchase, includes multiple order items |
| `OrderItem` | Line items for each product within an order       |
| `Reviews`   | Ratings & feedback tied to orders & products      |

---

## 🧩 Known Issues & Upcoming Features

* 🔄 **Payment Gateway Integration**
  Add Stripe or Razorpay to enable actual transactions.

* 👤 **User Profile Management**
  Allow users to update personal info and view profiles.

* 🔍 **Advanced Product Search**
  Filter by category, price range, availability, etc.

---

## 🏁 How to Run

1. Ensure **MySQL** is running and a database named `test` is created.
2. Update the `application.properties` file with your DB credentials.
3. Run using Maven:

```bash
mvn spring-boot:run
```

4. Visit: `http://localhost:8080`

---
💻 A Call for Frontend Developers!
This backend is ready and waiting for a beautiful frontend to bring it to life. If you are a frontend developer looking for a solid API to build upon, please feel free to use this project.

Your challenge, should you choose to accept it, is to build a complete frontend experience for NexusMart and push your code to me. I'm excited to see what you create!


## 👨‍💻 Author

**KalaiSelvam M**
*Full-Stack Developer in Progress* 🚀

