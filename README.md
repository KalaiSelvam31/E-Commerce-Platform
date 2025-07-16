# 🚀 NexusMart E-Commerce Backend

A robust, full-featured REST API backend for the **NexusMart** e-commerce platform, built with Spring Boot. It provides a complete set of services for user management, product catalog administration, shopping cart operations, and order processing.

---

## ✅ Features Implemented

- **User Authentication:** Secure user registration and login using JWT (JSON Web Tokens).
- **Role-Based Access Control:** Distinct roles for `User` and `Admin`, with endpoints protected using `@PreAuthorize`.
- **Full Product Management (Admin):** Admins can add new products and update existing product details, including price and stock status.
- **Advanced Shopping Cart:**
    - Add items to the cart.
    - View all items in the cart.
    - Update the quantity of items in the cart.
    - Remove items from the cart.
- **Complete Order Lifecycle:**
    - Place an order from the items in the cart (Checkout).
    - Automatically clears the cart after a successful checkout.
    - View a detailed history of all past orders for a user.
- **Product Discovery:**
    - Fetch a list of all available products.
    - Search for products by name with partial matching.
- **Robust Error Handling:** Global exception handlers for common issues like product not found, out of stock, and invalid input.

---

## 📁 Project Structure


src/main/java/com/example/test/

├── Controller/         → REST endpoints for all features
|
├── Model/              → JPA Entity classes (Users, Product, Cart, etc.)
|
├── Service/            → Business logic for all operations
|
├── Repository/         → Spring Data JPA Repositories
|
├── Filter/             → JWT authentication filter
|
├── Configuration/      → Spring Security and application configuration
|
└── Advice/Exception/   → Global exception handling


---

## ⚙️ Technologies Used

- **Java 21** & **Spring Boot 3.5.3**
- **Spring Security:** For authentication and authorization.
- **Spring Data JPA & Hibernate:** For database interaction.
- **MySQL:** Relational database.
- **Lombok:** To reduce boilerplate code.
- **JWT (jjwt):** For stateless API authentication.
- **Maven:** For dependency management.

---

## 📌 API Endpoint Documentation

The base URL is `http://localhost:8080`.

### **Authentication**
| Method | Endpoint      | Description                               |
| :---   | :---          | :---                                      |
| `POST` | `/Register`   | Registers a new user with `User` role.    |
| `POST` | `/Login`      | Authenticates a user and returns a JWT.   |

### **Products (Public)**
| Method | Endpoint              | Description                               |
| :---   | :---                  | :---                                      |
| `GET`  | `/getProduct`         | Retrieves a list of all products.         |
| `GET`  | `/Search/{productname}` | Searches for products by name.            |

### **Cart Management**
| Method | Endpoint                                  | Description                               |
| :---   | :---                                      | :---                                      |
| `GET`  | `/viewCart/{userid}`                      | Views all items in a user's cart.         |
| `POST` | `/addToCart/{userid}/{productid}`         | Adds a product to the user's cart.        |
| `POST` | `/updateCart/{userid}/{productid}/{quantity}` | Updates the quantity of a product in the cart. |
| `POST` | `/removeCart/{userid}/{productid}`        | Removes a product from the user's cart.   |

### **Orders**
| Method | Endpoint              | Description                               |
| :---   | :---                  | :---                                      |
| `POST` | `/Checkout/{userid}`  | Creates an order from the user's cart.    |
| `GET`  | `/orderDetails/{userid}`| Retrieves a user's complete order history.|

### **Admin Panel**
*(Requires `Admin` role)*
| Method | Endpoint                                  | Description                               |
| :---   | :---                                      | :---                                      |
| `POST` | `/addProducts`                            | Adds a new product to the store.          |
| `POST` | `/updateProducts/{productid}/{bool}/{price}` | Updates a product's stock status and price. |

---

## 📦 Entities

- **Users:** Stores user credentials and roles (`User`, `Admin`).
- **Product:** Represents items available for purchase.
- **Cart:** A user’s temporary holding area for products before checkout.
- **Orders:** Represents a finalized purchase, linked to a user.
- **OrderItem:** Represents a single line item within an order, linking `Orders` and `Product`.

---

## 💡 Known Issues & Future Improvements

- **Order ID in Response:** The `/orderDetails/{userid}` endpoint currently returns `orderid: 0`. **To fix this**, add `op.setOrderid(o.getOrderId());` inside the loop in the `orderDetails` method in `StoreService.java`.
- **Payment Gateway:** Integrate a payment provider like Stripe or Razorpay to handle actual transactions during checkout.
- **User Profile:** Add endpoints for users to view and update their own profile information.
- **Advanced Search:** Implement more advanced filtering and sorting options for products (e.g., by category, price range).

---

## 🏁 Running the App

1.  Ensure MySQL is running and a database named `test` has been created.
2.  Update the `spring.datasource` properties in `application.properties` if your credentials differ.
3.  Run the application using Maven:
    ```bash
    mvn spring-boot:run
    ```
The application will start at `http://localhost:8080/`.

---

## 🧠 Author

KalaiSelvam M — *Full-Stack Developer in Progress* 🚀
