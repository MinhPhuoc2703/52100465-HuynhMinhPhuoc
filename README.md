# Demo video is in a zip file submitted on elearning
# Spring Commerce

A simple sales website built on the Spring Boot platform. This website provides basic functions such as product management, search, registration, login, add to cart, payment and other features.

## Technologies 
- Java
- Spring boot
- Spring Data JPA 
- Spring security 
- Spring Web
- Postman
- MySQL
- Docker
- Thymeleaf
- Bootstrap

## Features

**Product Management (CRUD):**

- Add, view, edit, delete products for Admin account.
- Use Thymeleaf to display product information directly on the page.

**Filter and Search:**
- Search for products based on criteria such as keywords.
- Filter products according to price.

**Register and Login:**
- Users can register a new account.
- Users log in to website.
- Users can logout after completing.

**Shopping Cart:**
- Add product to cart.
- View and manage shopping cart.

**Payment:**
- Complete the checkout process with shipping and payment information.


**Security:**
- Password Encoder
- User authentication
- Role Permission

## Database Implementation
- MySQL
   * **Users** 
   * **Cart** 
   * **Products** 
   * **Role** 
   
![mysql](https://user-images.githubusercontent.com/117083387/284830825-c7e24207-6cf6-447d-bf80-ce15e572b7d7.png)
> MySQL database schema






## How to run this application 
To clone and run this application, ensure that you have installed at least Java 21 and all JDK tools (including JRE), Maven to build dependencies,IntelliJ,docker-compose. Then, follow these instructions:


- Download this project by git clone or download zip file

- Open this project using IntelliJ

- Run ```SpringcommerceApplication.java```
  > This is gonna be building the maven dependencies too

- The endpoints are located in 'http://localhost:8080/' and config its port on ```src/main/resources/application.properties```
  
  
- Make sure to create a database called **cocktail_shop** 
  > or create it with another name. However, you must to rename its name in ```src/main/resources/application.properties```

  > use docker-compose to create database on ```docker/docker-compose.yml``` with the following commands:
     docker-compose up -d
    

 **By the way, you can change the port (8080) to another one, just change the line in ```src/main/resources/application.properties```**

  **Now, you are able to run this Java application locally.** 

## API Reference
## This project does not use restful api
####  Get all products

```http
  GET /products/all_products
```

#### Add Product 

```http
  POST /api/products/add
```


#### Delete Product 

```http
  GET /api/products/delete
```


#### Update Product 

```http
  POST /api/products/edit
```


#### Add to Cart

```http
  GET  /products/cart/add
```

#### Remove from Cart

```http
  GET /products/cart/remove
```


#### Payment

```http
 GET /products/cart/payment
```


#### Show product detail

```http
 GET /products/details/
```


#### Search Products
```http
 GET /products/search
```


#### Filter Products by Price
```http
 GET /products/filter
```


#### Place Order
```http
 POST /products/order
```


### Usage
**add products**
![Get all products](./springcommere/Demo/add-product.png)

**delete products**
![delete products](./springcommere/Demo/delete-product.png)

**edit products**
![edit products](./springcommere/Demo/edit-product.png)

**filter products**
![filter products](./springcommere/Demo/filter-product.png)

**login**
![login](./springcommere/Demo/login.png)

**payment**
![payment](./springcommere/Demo/payment.png)

**products detail**
![product detail](.//springcommereDemo/product-detail.png)

**search product**
![login](./springcommere/Demo/search-product.png)

**show cart**
![login](./springcommere/Demo/show-cart.png)
