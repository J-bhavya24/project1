# 🍕 AI Pizza Delivery System — Complete Spring Boot Backend Guide
### Step-by-Step From Project Creation to Final Implementation

---

## 📋 TABLE OF CONTENTS

1. [Create Spring Boot Project (Step by Step)](#step-1-create-spring-boot-project)
2. [Final Package Structure](#step-2-final-package-structure)
3. [application.properties](#step-3-applicationproperties)
4. [All Entity Classes](#step-4-all-entity-classes)
5. [All Repository Interfaces](#step-5-all-repository-interfaces)
6. [All DTO Classes](#step-6-all-dto-classes)
7. [All Service Classes (Full Implementation)](#step-7-all-service-classes)
8. [All Controller Classes](#step-8-all-controller-classes)
9. [Security & Config Classes](#step-9-security--config-classes)
10. [Main Application Class](#step-10-main-application-class)
11. [How to Run](#step-11-how-to-run)
12. [API Testing Guide](#step-12-api-testing-guide)

---

## STEP 1: CREATE SPRING BOOT PROJECT

### 1.1 — Go to https://start.spring.io

Fill in exactly:
```
Project      : Maven
Language     : Java
Spring Boot  : 3.2.5
Group        : com.pizza
Artifact     : pizzaapp
Name         : pizzaapp
Package name : com.pizza.pizzaapp
Packaging    : Jar
Java         : 17
```

### 1.2 — Add these Dependencies (search and select each):
```
- Spring Web
- Spring Data JPA
- Spring Security
- MySQL Driver
- Lombok
- Validation
- WebSocket
```

Click **GENERATE** → it downloads `pizzaapp.zip`  
Extract the zip → Open in IntelliJ IDEA or VS Code

---

### 1.3 — Replace pom.xml completely with this:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.2.5</version>
    <relativePath/>
  </parent>

  <groupId>com.pizza</groupId>
  <artifactId>pizzaapp</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <name>pizzaapp</name>
  <description>AI Pizza Delivery System</description>

  <properties>
    <java.version>17</java.version>
  </properties>

  <dependencies>

    <!-- Spring Web -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Data JPA -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Spring Security -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- Validation -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- WebSocket -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-websocket</artifactId>
    </dependency>

    <!-- MySQL Driver -->
    <dependency>
      <groupId>com.mysql</groupId>
      <artifactId>mysql-connector-j</artifactId>
      <scope>runtime</scope>
    </dependency>

    <!-- Lombok -->
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <optional>true</optional>
    </dependency>

    <!-- JWT -->
    <dependency>
      <groupId>io.jsonwebtoken</groupId>
      <artifactId>jjwt-api</artifactId>
      <version>0.11.5</version>
    </dependency>
    <dependency>
      <groupId>io.jsonwebtoken</groupId>
      <artifactId>jjwt-impl</artifactId>
      <version>0.11.5</version>
      <scope>runtime</scope>
    </dependency>
    <dependency>
      <groupId>io.jsonwebtoken</groupId>
      <artifactId>jjwt-jackson</artifactId>
      <version>0.11.5</version>
      <scope>runtime</scope>
    </dependency>

    <!-- Razorpay -->
    <dependency>
      <groupId>com.razorpay</groupId>
      <artifactId>razorpay-java</artifactId>
      <version>1.4.5</version>
    </dependency>

    <!-- Test -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>

  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
          <excludes>
            <exclude>
              <groupId>org.projectlombok</groupId>
              <artifactId>lombok</artifactId>
            </exclude>
          </excludes>
        </configuration>
      </plugin>
    </plugins>
  </build>

</project>
```

---

## STEP 2: FINAL PACKAGE STRUCTURE

Inside `src/main/java/com/pizza/pizzaapp/` create EXACTLY these folders and files:

```
com.pizza.pizzaapp
│
├── PizzaappApplication.java                  ← main class (already exists)
│
├── config/
│   ├── SecurityConfig.java
│   ├── WebSocketConfig.java
│   └── JwtAuthFilter.java
│
├── entity/
│   ├── User.java
│   ├── Role.java                             ← enum
│   ├── MenuItem.java
│   ├── Cart.java
│   ├── CartItem.java
│   ├── Order.java
│   ├── OrderItem.java
│   ├── OrderStatus.java                      ← enum
│   ├── Coupon.java
│   ├── DeliveryAgent.java
│   ├── DeliveryTracking.java
│   └── Payment.java
│
├── repository/
│   ├── UserRepository.java
│   ├── MenuItemRepository.java
│   ├── CartRepository.java
│   ├── CartItemRepository.java
│   ├── OrderRepository.java
│   ├── OrderItemRepository.java
│   ├── CouponRepository.java
│   ├── DeliveryAgentRepository.java
│   ├── DeliveryTrackingRepository.java
│   └── PaymentRepository.java
│
├── dto/
│   ├── RegisterRequest.java
│   ├── LoginRequest.java
│   ├── AuthResponse.java
│   ├── MenuItemDTO.java
│   ├── CartItemRequest.java
│   ├── OrderRequest.java
│   ├── OrderItemRequest.java
│   ├── OrderResponse.java
│   ├── PaymentRequest.java
│   ├── PaymentResponse.java
│   ├── PaymentVerifyRequest.java
│   ├── LocationUpdateRequest.java
│   └── ApiResponse.java
│
├── service/
│   ├── CustomUserDetailsService.java
│   ├── JwtService.java
│   ├── AuthService.java
│   ├── MenuService.java
│   ├── CartService.java
│   ├── OrderService.java
│   ├── CouponService.java
│   ├── RecommendationService.java
│   ├── DeliveryService.java
│   ├── PaymentService.java
│   └── AnalyticsService.java
│
└── controller/
    ├── AuthController.java
    ├── MenuController.java
    ├── CartController.java
    ├── OrderController.java
    ├── CouponController.java
    ├── RecommendationController.java
    ├── DeliveryController.java
    ├── PaymentController.java
    └── AnalyticsController.java
```

---

## STEP 3: application.properties

File location: `src/main/resources/application.properties`

```properties
# ===== SERVER =====
server.port=8080

# ===== MYSQL DATABASE =====
# First create the database in MySQL:
# CREATE DATABASE pizzadb;
spring.datasource.url=jdbc:mysql://localhost:3306/pizzadb?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# ===== JPA =====
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.properties.hibernate.format_sql=true

# ===== JWT =====
jwt.secret=MySuperSecretKeyForPizzaAppThatIsVeryLongAndSecure123456789
jwt.expiration=86400000

# ===== RAZORPAY =====
razorpay.key.id=YOUR_RAZORPAY_KEY_ID
razorpay.key.secret=YOUR_RAZORPAY_KEY_SECRET
```

---

## STEP 4: ALL ENTITY CLASSES

### 📄 entity/Role.java
```java
package com.pizza.pizzaapp.entity;

public enum Role {
    CUSTOMER,
    ADMIN,
    DELIVERY
}
```

---

### 📄 entity/OrderStatus.java
```java
package com.pizza.pizzaapp.entity;

public enum OrderStatus {
    PLACED,
    CONFIRMED,
    PREPARING,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED
}
```

---

### 📄 entity/User.java
```java
package com.pizza.pizzaapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phone;

    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
```

---

### 📄 entity/MenuItem.java
```java
package com.pizza.pizzaapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    // E.g. PIZZA, SIDE, DRINK, DESSERT
    private String category;

    // E.g. SMALL, MEDIUM, LARGE
    private String size;

    @Column(nullable = false)
    private Double price;

    private boolean available;

    private String imageUrl;

    // For seasonal recommendations: SPRING, SUMMER, AUTUMN, WINTER
    private String season;
}
```

---

### 📄 entity/Cart.java
```java
package com.pizza.pizzaapp.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<CartItem> items = new ArrayList<>();
}
```

---

### 📄 entity/CartItem.java
```java
package com.pizza.pizzaapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    @Column(nullable = false)
    private Integer quantity;
}
```

---

### 📄 entity/Order.java
```java
package com.pizza.pizzaapp.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<OrderItem> items = new ArrayList<>();

    private Double totalAmount;
    private Double discount;
    private Double taxAmount;
    private Double finalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String deliveryAddress;

    private String couponCode;

    private LocalDateTime placedAt;

    private LocalDateTime deliveredAt;
}
```

---

### 📄 entity/OrderItem.java
```java
package com.pizza.pizzaapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;
}
```

---

### 📄 entity/Coupon.java
```java
package com.pizza.pizzaapp.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "coupons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    // Discount percentage e.g. 20 means 20%
    private Double discountPercent;

    // Maximum discount cap in rupees
    private Double maxDiscount;

    private LocalDate expiryDate;

    private boolean active;
}
```

---

### 📄 entity/DeliveryAgent.java
```java
package com.pizza.pizzaapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "delivery_agents")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    private String vehicleNumber;

    // true = free to take new delivery
    private boolean available;

    private Double currentLat;

    private Double currentLng;
}
```

---

### 📄 entity/DeliveryTracking.java
```java
package com.pizza.pizzaapp.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_tracking")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", unique = true)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private DeliveryAgent agent;

    private Double currentLat;
    private Double currentLng;
    private Double destinationLat;
    private Double destinationLng;

    // ASSIGNED, PICKED_UP, EN_ROUTE, DELIVERED
    private String trackingStatus;

    private LocalDateTime estimatedArrival;
}
```

---

### 📄 entity/Payment.java
```java
package com.pizza.pizzaapp.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", unique = true)
    private Order order;

    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;

    private Double amount;
    private String currency;

    // CREATED, SUCCESS, FAILED
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
```

---

## STEP 5: ALL REPOSITORY INTERFACES

> Repositories talk to the database. Spring auto-implements them — you only write the interface.

---

### 📄 repository/UserRepository.java
```java
package com.pizza.pizzaapp.repository;

import com.pizza.pizzaapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
```

---

### 📄 repository/MenuItemRepository.java
```java
package com.pizza.pizzaapp.repository;

import com.pizza.pizzaapp.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByCategoryIgnoreCase(String category);
    List<MenuItem> findByAvailableTrue();
    List<MenuItem> findBySeason(String season);
    List<MenuItem> findByCategoryIgnoreCaseAndAvailableTrue(String category);
}
```

---

### 📄 repository/CartRepository.java
```java
package com.pizza.pizzaapp.repository;

import com.pizza.pizzaapp.entity.Cart;
import com.pizza.pizzaapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
```

---

### 📄 repository/CartItemRepository.java
```java
package com.pizza.pizzaapp.repository;

import com.pizza.pizzaapp.entity.Cart;
import com.pizza.pizzaapp.entity.CartItem;
import com.pizza.pizzaapp.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndMenuItem(Cart cart, MenuItem menuItem);
}
```

---

### 📄 repository/OrderRepository.java
```java
package com.pizza.pizzaapp.repository;

import com.pizza.pizzaapp.entity.Order;
import com.pizza.pizzaapp.entity.OrderStatus;
import com.pizza.pizzaapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserOrderByPlacedAtDesc(User user);
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByPlacedAtAfter(LocalDateTime date);

    @Query("SELECT o FROM Order o JOIN o.items oi WHERE oi.menuItem.id = :itemId")
    List<Order> findOrdersContainingItem(@Param("itemId") Long itemId);
}
```

---

### 📄 repository/OrderItemRepository.java
```java
package com.pizza.pizzaapp.repository;

import com.pizza.pizzaapp.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
```

---

### 📄 repository/CouponRepository.java
```java
package com.pizza.pizzaapp.repository;

import com.pizza.pizzaapp.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByCodeAndActiveTrue(String code);
}
```

---

### 📄 repository/DeliveryAgentRepository.java
```java
package com.pizza.pizzaapp.repository;

import com.pizza.pizzaapp.entity.DeliveryAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, Long> {
    Optional<DeliveryAgent> findFirstByAvailableTrue();
}
```

---

### 📄 repository/DeliveryTrackingRepository.java
```java
package com.pizza.pizzaapp.repository;

import com.pizza.pizzaapp.entity.DeliveryTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DeliveryTrackingRepository extends JpaRepository<DeliveryTracking, Long> {
    Optional<DeliveryTracking> findByOrderId(Long orderId);
}
```

---

### 📄 repository/PaymentRepository.java
```java
package com.pizza.pizzaapp.repository;

import com.pizza.pizzaapp.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByRazorpayOrderId(String razorpayOrderId);
    Optional<Payment> findByOrderId(Long orderId);
}
```

---

## STEP 6: ALL DTO CLASSES

> DTOs (Data Transfer Objects) are what you send/receive in HTTP requests.  
> They are plain classes — NO @Entity annotation.

---

### 📄 dto/RegisterRequest.java
```java
package com.pizza.pizzaapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Enter valid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private String phone;
    private String address;

    // Send: CUSTOMER, ADMIN, or DELIVERY
    // Default will be CUSTOMER if not sent
    private String role;
}
```

---

### 📄 dto/LoginRequest.java
```java
package com.pizza.pizzaapp.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
```

---

### 📄 dto/AuthResponse.java
```java
package com.pizza.pizzaapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String role;
    private String email;
    private String name;
}
```

---

### 📄 dto/MenuItemDTO.java
```java
package com.pizza.pizzaapp.dto;

import lombok.Data;

@Data
public class MenuItemDTO {
    private String name;
    private String description;
    private String category;
    private String size;
    private Double price;
    private boolean available;
    private String imageUrl;
    private String season;
}
```

---

### 📄 dto/CartItemRequest.java
```java
package com.pizza.pizzaapp.dto;

import lombok.Data;

@Data
public class CartItemRequest {
    private Long menuItemId;
    private Integer quantity;
}
```

---

### 📄 dto/OrderItemRequest.java
```java
package com.pizza.pizzaapp.dto;

import lombok.Data;

@Data
public class OrderItemRequest {
    private Long menuItemId;
    private Integer quantity;
}
```

---

### 📄 dto/OrderRequest.java
```java
package com.pizza.pizzaapp.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    private List<OrderItemRequest> items;
    private String deliveryAddress;
    private String couponCode;     // optional — can be null
}
```

---

### 📄 dto/OrderResponse.java
```java
package com.pizza.pizzaapp.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderResponse {
    private Long orderId;
    private Double totalAmount;
    private Double discount;
    private Double taxAmount;
    private Double finalAmount;
    private String status;
    private String deliveryAddress;
    private LocalDateTime placedAt;
}
```

---

### 📄 dto/PaymentRequest.java
```java
package com.pizza.pizzaapp.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long orderId;
    private String currency;   // e.g. INR
}
```

---

### 📄 dto/PaymentResponse.java
```java
package com.pizza.pizzaapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentResponse {
    private String razorpayOrderId;
    private Double amount;
    private String currency;
    private String key;
}
```

---

### 📄 dto/PaymentVerifyRequest.java
```java
package com.pizza.pizzaapp.dto;

import lombok.Data;

@Data
public class PaymentVerifyRequest {
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;
}
```

---

### 📄 dto/LocationUpdateRequest.java
```java
package com.pizza.pizzaapp.dto;

import lombok.Data;

@Data
public class LocationUpdateRequest {
    private Long trackingId;
    private Double lat;
    private Double lng;
}
```

---

### 📄 dto/ApiResponse.java
```java
package com.pizza.pizzaapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

// Generic response wrapper for success/error messages
@Data
@AllArgsConstructor
public class ApiResponse {
    private boolean success;
    private String message;
}
```

---

## STEP 7: ALL SERVICE CLASSES

> This is the BRAIN of the app — all business logic lives here.

---

### 📄 service/JwtService.java
```java
package com.pizza.pizzaapp.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    // Converts the secret string into a cryptographic key
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Generate a token for a user
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())     // username = email
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract the email from a token
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Check if the token is valid and not expired
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String email = extractEmail(token);
        return email.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiry = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiry.before(new Date());
    }
}
```

---

### 📄 service/CustomUserDetailsService.java
```java
package com.pizza.pizzaapp.service;

import com.pizza.pizzaapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // Spring Security calls this to load user during login
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        // Prefix ROLE_ is required by Spring Security
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }
}
```

---

### 📄 service/AuthService.java
```java
package com.pizza.pizzaapp.service;

import com.pizza.pizzaapp.dto.*;
import com.pizza.pizzaapp.entity.*;
import com.pizza.pizzaapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;

    public ApiResponse register(RegisterRequest request) {
        // Check if email is already registered
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered. Please login.");
        }

        // Default role is CUSTOMER
        Role role = Role.CUSTOMER;
        if (request.getRole() != null && !request.getRole().isEmpty()) {
            try {
                role = Role.valueOf(request.getRole().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid role. Use: CUSTOMER, ADMIN, or DELIVERY");
            }
        }

        // Build and save user
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // encode password
                .phone(request.getPhone())
                .address(request.getAddress())
                .role(role)
                .build();

        userRepository.save(user);
        return new ApiResponse(true, "Registration successful!");
    }

    public AuthResponse login(LoginRequest request) {
        // This will throw if credentials are wrong
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Load the user and generate token
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtService.generateToken(userDetails);

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        return new AuthResponse(token, user.getRole().name(), user.getEmail(), user.getName());
    }
}
```

---

### 📄 service/MenuService.java
```java
package com.pizza.pizzaapp.service;

import com.pizza.pizzaapp.dto.MenuItemDTO;
import com.pizza.pizzaapp.entity.MenuItem;
import com.pizza.pizzaapp.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuItemRepository menuItemRepository;

    // Get all available menu items
    public List<MenuItem> getAllAvailableItems() {
        return menuItemRepository.findByAvailableTrue();
    }

    // Get all items (including unavailable) — for admin
    public List<MenuItem> getAllItems() {
        return menuItemRepository.findAll();
    }

    // Get items by category
    public List<MenuItem> getByCategory(String category) {
        return menuItemRepository.findByCategoryIgnoreCaseAndAvailableTrue(category);
    }

    // Get a single item by ID
    public MenuItem getById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found with id: " + id));
    }

    // Admin: Add new menu item
    public MenuItem addItem(MenuItemDTO dto) {
        MenuItem item = MenuItem.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .size(dto.getSize())
                .price(dto.getPrice())
                .available(dto.isAvailable())
                .imageUrl(dto.getImageUrl())
                .season(dto.getSeason())
                .build();
        return menuItemRepository.save(item);
    }

    // Admin: Update existing item
    public MenuItem updateItem(Long id, MenuItemDTO dto) {
        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found with id: " + id));

        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setCategory(dto.getCategory());
        item.setSize(dto.getSize());
        item.setPrice(dto.getPrice());
        item.setAvailable(dto.isAvailable());
        item.setImageUrl(dto.getImageUrl());
        item.setSeason(dto.getSeason());

        return menuItemRepository.save(item);
    }

    // Admin: Delete item
    public ApiResponse deleteItem(Long id) {
        if (!menuItemRepository.existsById(id)) {
            throw new RuntimeException("Menu item not found with id: " + id);
        }
        menuItemRepository.deleteById(id);
        return new ApiResponse(true, "Item deleted successfully");
    }

    // Smart combo suggestions: if user picks a pizza, suggest sides and drinks
    public List<MenuItem> getComboSuggestions(Long menuItemId) {
        MenuItem item = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        // If item is PIZZA → suggest SIDE and DRINK
        if ("PIZZA".equalsIgnoreCase(item.getCategory())) {
            return menuItemRepository.findByCategoryIgnoreCaseAndAvailableTrue("SIDE");
        }
        // If item is SIDE → suggest DRINK
        if ("SIDE".equalsIgnoreCase(item.getCategory())) {
            return menuItemRepository.findByCategoryIgnoreCaseAndAvailableTrue("DRINK");
        }
        // Otherwise return top items
        return menuItemRepository.findByAvailableTrue()
                .stream().limit(5).toList();
    }

    // Helper used by MenuService (needed by AnalyticsService too)
    public ApiResponse deleteItemHelper(Long id) {
        menuItemRepository.deleteById(id);
        return new ApiResponse(true, "Deleted");
    }
}
```

---

### 📄 service/CartService.java
```java
package com.pizza.pizzaapp.service;

import com.pizza.pizzaapp.dto.CartItemRequest;
import com.pizza.pizzaapp.entity.*;
import com.pizza.pizzaapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final MenuItemRepository menuItemRepository;
    private final UserRepository userRepository;

    // Get the cart for a user (create if not exists)
    public Cart getCart(String email) {
        User user = getUserByEmail(email);
        return cartRepository.findByUser(user)
                .orElseGet(() -> cartRepository.save(
                        Cart.builder().user(user).build()
                ));
    }

    // Add item to cart OR increase quantity if already present
    public Cart addItem(String email, CartItemRequest request) {
        User user = getUserByEmail(email);
        MenuItem menuItem = menuItemRepository.findById(request.getMenuItemId())
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> cartRepository.save(Cart.builder().user(user).build()));

        // Check if item already exists in cart
        var existingItem = cartItemRepository.findByCartAndMenuItem(cart, menuItem);

        if (existingItem.isPresent()) {
            // Just increase quantity
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
            cartItemRepository.save(cartItem);
        } else {
            // Add new cart item
            CartItem newItem = CartItem.builder()
                    .cart(cart)
                    .menuItem(menuItem)
                    .quantity(request.getQuantity())
                    .build();
            cart.getItems().add(newItem);
        }

        return cartRepository.save(cart);
    }

    // Remove a specific item from the cart
    public Cart removeItem(String email, Long cartItemId) {
        User user = getUserByEmail(email);
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getItems().removeIf(item -> item.getId().equals(cartItemId));
        return cartRepository.save(cart);
    }

    // Clear entire cart (called after order is placed)
    public void clearCart(String email) {
        User user = getUserByEmail(email);
        Cart cart = cartRepository.findByUser(user).orElse(null);
        if (cart != null) {
            cart.getItems().clear();
            cartRepository.save(cart);
        }
    }

    // Update quantity of a specific cart item
    public Cart updateQuantity(String email, Long cartItemId, Integer quantity) {
        User user = getUserByEmail(email);
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .ifPresent(item -> {
                    if (quantity <= 0) {
                        cart.getItems().remove(item);
                    } else {
                        item.setQuantity(quantity);
                    }
                });

        return cartRepository.save(cart);
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
```

---

### 📄 service/CouponService.java
```java
package com.pizza.pizzaapp.service;

import com.pizza.pizzaapp.entity.Coupon;
import com.pizza.pizzaapp.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    // Admin: Create a new coupon
    public Coupon createCoupon(Coupon coupon) {
        if (couponRepository.findByCodeAndActiveTrue(coupon.getCode()).isPresent()) {
            throw new RuntimeException("Coupon code already exists");
        }
        coupon.setActive(true);
        return couponRepository.save(coupon);
    }

    // Validate and return discount amount
    public double validateAndGetDiscount(String code, double orderTotal) {
        Coupon coupon = couponRepository.findByCodeAndActiveTrue(code)
                .orElseThrow(() -> new RuntimeException("Invalid or expired coupon: " + code));

        // Check expiry
        if (coupon.getExpiryDate() != null && coupon.getExpiryDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Coupon has expired");
        }

        // Calculate discount
        double rawDiscount = orderTotal * coupon.getDiscountPercent() / 100.0;
        return Math.min(rawDiscount, coupon.getMaxDiscount());
    }

    // Get all active coupons
    public List<Coupon> getAllActiveCoupons() {
        return couponRepository.findAll().stream()
                .filter(Coupon::isActive)
                .toList();
    }

    // Admin: Deactivate a coupon
    public Coupon deactivateCoupon(Long id) {
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));
        coupon.setActive(false);
        return couponRepository.save(coupon);
    }
}
```

---

### 📄 service/OrderService.java
```java
package com.pizza.pizzaapp.service;

import com.pizza.pizzaapp.dto.OrderRequest;
import com.pizza.pizzaapp.dto.OrderResponse;
import com.pizza.pizzaapp.entity.*;
import com.pizza.pizzaapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;
    private final UserRepository userRepository;
    private final CouponService couponService;
    private final CartService cartService;

    @Transactional
    public OrderResponse placeOrder(String email, OrderRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Build order items and calculate total
        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0.0;

        for (var itemReq : request.getItems()) {
            MenuItem menuItem = menuItemRepository.findById(itemReq.getMenuItemId())
                    .orElseThrow(() -> new RuntimeException(
                            "Menu item not found: " + itemReq.getMenuItemId()));

            if (!menuItem.isAvailable()) {
                throw new RuntimeException(menuItem.getName() + " is currently unavailable");
            }

            double itemTotal = menuItem.getPrice() * itemReq.getQuantity();
            total += itemTotal;

            OrderItem orderItem = OrderItem.builder()
                    .menuItem(menuItem)
                    .quantity(itemReq.getQuantity())
                    .price(itemTotal)
                    .build();
            orderItems.add(orderItem);
        }

        // Apply coupon discount if provided
        double discount = 0.0;
        if (request.getCouponCode() != null && !request.getCouponCode().isEmpty()) {
            try {
                discount = couponService.validateAndGetDiscount(request.getCouponCode(), total);
            } catch (RuntimeException e) {
                throw new RuntimeException("Coupon error: " + e.getMessage());
            }
        }

        // Calculate GST (18%)
        double taxableAmount = total - discount;
        double taxAmount = taxableAmount * 0.18;
        double finalAmount = taxableAmount + taxAmount;

        // Create the Order
        Order order = Order.builder()
                .user(user)
                .totalAmount(total)
                .discount(discount)
                .taxAmount(taxAmount)
                .finalAmount(finalAmount)
                .status(OrderStatus.PLACED)
                .deliveryAddress(request.getDeliveryAddress())
                .couponCode(request.getCouponCode())
                .placedAt(LocalDateTime.now())
                .build();

        Order savedOrder = orderRepository.save(order);

        // Link each order item back to this order and save
        orderItems.forEach(item -> item.setOrder(savedOrder));
        savedOrder.setItems(orderItems);
        orderRepository.save(savedOrder);

        // Clear the user's cart after placing order
        cartService.clearCart(email);

        // Build and return response
        return buildOrderResponse(savedOrder);
    }

    // Get all orders placed by a specific user
    public List<OrderResponse> getMyOrders(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return orderRepository.findByUserOrderByPlacedAtDesc(user)
                .stream()
                .map(this::buildOrderResponse)
                .collect(Collectors.toList());
    }

    // Admin: Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get single order by ID
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
    }

    // Admin / Delivery: Update order status
    public OrderResponse updateStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));

        try {
            order.setStatus(OrderStatus.valueOf(status.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status. Use: PLACED, CONFIRMED, PREPARING, " +
                    "OUT_FOR_DELIVERY, DELIVERED, CANCELLED");
        }

        if (OrderStatus.DELIVERED.name().equalsIgnoreCase(status)) {
            order.setDeliveredAt(LocalDateTime.now());
        }

        return buildOrderResponse(orderRepository.save(order));
    }

    // Customer: Cancel their own order
    public OrderResponse cancelOrder(Long orderId, String email) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getUser().getEmail().equals(email)) {
            throw new RuntimeException("You are not allowed to cancel this order");
        }

        if (order.getStatus() != OrderStatus.PLACED) {
            throw new RuntimeException("Order cannot be cancelled at this stage");
        }

        order.setStatus(OrderStatus.CANCELLED);
        return buildOrderResponse(orderRepository.save(order));
    }

    // Helper: Convert Order entity to OrderResponse DTO
    private OrderResponse buildOrderResponse(Order order) {
        OrderResponse res = new OrderResponse();
        res.setOrderId(order.getId());
        res.setTotalAmount(order.getTotalAmount());
        res.setDiscount(order.getDiscount());
        res.setTaxAmount(order.getTaxAmount());
        res.setFinalAmount(order.getFinalAmount());
        res.setStatus(order.getStatus().name());
        res.setDeliveryAddress(order.getDeliveryAddress());
        res.setPlacedAt(order.getPlacedAt());
        return res;
    }
}
```

---

### 📄 service/RecommendationService.java
```java
package com.pizza.pizzaapp.service;

import com.pizza.pizzaapp.entity.*;
import com.pizza.pizzaapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;
    private final UserRepository userRepository;

    /**
     * PERSONALIZED: Based on what THIS user has ordered most in the past.
     * Counts how many times each item appears in their order history
     * and returns the top 5.
     */
    public List<MenuItem> getPersonalizedRecommendations(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Order> pastOrders = orderRepository.findByUserOrderByPlacedAtDesc(user);

        if (pastOrders.isEmpty()) {
            // New user — return top items overall
            return menuItemRepository.findByAvailableTrue()
                    .stream().limit(5).collect(Collectors.toList());
        }

        // Count frequency of each menu item in past orders
        Map<Long, Long> itemFrequency = pastOrders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                        item -> item.getMenuItem().getId(),
                        Collectors.counting()
                ));

        // Sort by highest frequency and return top 5
        return itemFrequency.entrySet().stream()
                .sorted(Map.Entry.<Long, Long>comparingByValue().reversed())
                .limit(5)
                .map(entry -> menuItemRepository.findById(entry.getKey())
                        .orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * SEASONAL: Returns items tagged with the current season.
     * Spring: Mar-May | Summer: Jun-Aug | Autumn: Sep-Nov | Winter: Dec-Feb
     */
    public List<MenuItem> getSeasonalRecommendations() {
        int month = LocalDate.now().getMonthValue();
        String season;

        if (month >= 3 && month <= 5) season = "SPRING";
        else if (month >= 6 && month <= 8) season = "SUMMER";
        else if (month >= 9 && month <= 11) season = "AUTUMN";
        else season = "WINTER";

        List<MenuItem> seasonal = menuItemRepository.findBySeason(season);

        // Fallback to all available if no seasonal items found
        if (seasonal.isEmpty()) {
            return menuItemRepository.findByAvailableTrue()
                    .stream().limit(5).collect(Collectors.toList());
        }
        return seasonal;
    }

    /**
     * COLLABORATIVE FILTERING: People who ordered item X also ordered Y.
     * Finds all orders containing the given item, then counts co-occurring items.
     */
    public List<MenuItem> getCollaborativeRecommendations(Long menuItemId) {
        List<Order> ordersWithItem = orderRepository.findOrdersContainingItem(menuItemId);

        if (ordersWithItem.isEmpty()) {
            return menuItemRepository.findByAvailableTrue()
                    .stream().limit(5).collect(Collectors.toList());
        }

        // Count how often other items appear alongside this item
        Map<Long, Long> coOccurrence = ordersWithItem.stream()
                .flatMap(order -> order.getItems().stream())
                .filter(item -> !item.getMenuItem().getId().equals(menuItemId))
                .collect(Collectors.groupingBy(
                        item -> item.getMenuItem().getId(),
                        Collectors.counting()
                ));

        return coOccurrence.entrySet().stream()
                .sorted(Map.Entry.<Long, Long>comparingByValue().reversed())
                .limit(5)
                .map(entry -> menuItemRepository.findById(entry.getKey()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * TIME-BASED: Recommend by time of day.
     * Morning (6-11): Breakfast | Noon (11-15): Lunch |
     * Evening (15-18): Snack | Night (18+): Dinner
     */
    public List<MenuItem> getTimeBasedRecommendations() {
        int hour = LocalTime.now().getHour();
        String category;

        if (hour >= 6 && hour < 11) category = "BREAKFAST";
        else if (hour >= 11 && hour < 15) category = "LUNCH";
        else if (hour >= 15 && hour < 18) category = "SNACK";
        else category = "DINNER";

        List<MenuItem> items = menuItemRepository.findByCategoryIgnoreCaseAndAvailableTrue(category);

        // Fallback: if no items for that category, return general available items
        if (items.isEmpty()) {
            return menuItemRepository.findByAvailableTrue()
                    .stream().limit(5).collect(Collectors.toList());
        }
        return items;
    }

    /**
     * ORDER HISTORY ANALYSIS: Shows a user's order pattern.
     * Returns item name → total times ordered (sorted descending)
     */
    public Map<String, Long> getOrderHistoryAnalysis(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Order> orders = orderRepository.findByUserOrderByPlacedAtDesc(user);

        return orders.stream()
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.groupingBy(
                        i -> i.getMenuItem().getName(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
```

---

### 📄 service/DeliveryService.java
```java
package com.pizza.pizzaapp.service;

import com.pizza.pizzaapp.dto.LocationUpdateRequest;
import com.pizza.pizzaapp.entity.*;
import com.pizza.pizzaapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryTrackingRepository trackingRepository;
    private final DeliveryAgentRepository agentRepository;
    private final OrderRepository orderRepository;
    private final SimpMessagingTemplate messagingTemplate; // for WebSocket

    /**
     * ASSIGN DELIVERY: Admin calls this after order is confirmed.
     * Finds the first available agent, assigns the order to them.
     */
    public DeliveryTracking assignDelivery(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));

        if (order.getStatus() != OrderStatus.CONFIRMED) {
            throw new RuntimeException(
                    "Order must be CONFIRMED before assigning delivery. Current: "
                    + order.getStatus());
        }

        // Get nearest available agent
        DeliveryAgent agent = agentRepository.findFirstByAvailableTrue()
                .orElseThrow(() -> new RuntimeException(
                        "No delivery agents available right now. Try again later."));

        // Mark agent as busy
        agent.setAvailable(false);
        agentRepository.save(agent);

        // Calculate ETA (30 minutes default for demo)
        LocalDateTime eta = LocalDateTime.now().plusMinutes(30);

        // Create tracking record
        DeliveryTracking tracking = DeliveryTracking.builder()
                .order(order)
                .agent(agent)
                .trackingStatus("ASSIGNED")
                .estimatedArrival(eta)
                .build();

        // Update order status
        order.setStatus(OrderStatus.OUT_FOR_DELIVERY);
        orderRepository.save(order);

        return trackingRepository.save(tracking);
    }

    /**
     * UPDATE LOCATION: Delivery agent updates their GPS position.
     * Also broadcasts the update via WebSocket so the customer sees it live.
     */
    public void updateLocation(LocationUpdateRequest request) {
        DeliveryTracking tracking = trackingRepository.findById(request.getTrackingId())
                .orElseThrow(() -> new RuntimeException("Tracking record not found"));

        tracking.setCurrentLat(request.getLat());
        tracking.setCurrentLng(request.getLng());
        trackingRepository.save(tracking);

        // Recalculate ETA based on remaining distance (if destination is set)
        if (tracking.getDestinationLat() != null && tracking.getDestinationLng() != null) {
            double distanceKm = calculateDistance(
                    request.getLat(), request.getLng(),
                    tracking.getDestinationLat(), tracking.getDestinationLng()
            );
            long minutesLeft = estimateMinutes(distanceKm);
            tracking.setEstimatedArrival(LocalDateTime.now().plusMinutes(minutesLeft));
            trackingRepository.save(tracking);
        }

        // Push live update to customer via WebSocket
        // Customer subscribes to: /topic/tracking/{orderId}
        Map<String, Object> update = new HashMap<>();
        update.put("lat", request.getLat());
        update.put("lng", request.getLng());
        update.put("status", tracking.getTrackingStatus());
        update.put("eta", tracking.getEstimatedArrival());

        messagingTemplate.convertAndSend(
                "/topic/tracking/" + tracking.getOrder().getId(),
                update
        );
    }

    /**
     * GET ETA: Customer calls this to know how far the agent is.
     */
    public Map<String, Object> getETA(Long orderId) {
        DeliveryTracking tracking = trackingRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("No tracking found for order: " + orderId));

        long minutesLeft = java.time.Duration.between(
                LocalDateTime.now(), tracking.getEstimatedArrival()
        ).toMinutes();

        Map<String, Object> eta = new HashMap<>();
        eta.put("status", tracking.getTrackingStatus());
        eta.put("minutesLeft", Math.max(minutesLeft, 0));
        eta.put("estimatedArrival", tracking.getEstimatedArrival());
        eta.put("agentName", tracking.getAgent().getName());
        eta.put("agentPhone", tracking.getAgent().getPhone());
        return eta;
    }

    /**
     * MARK DELIVERED: Delivery agent marks delivery as complete.
     */
    public DeliveryTracking markDelivered(Long trackingId) {
        DeliveryTracking tracking = trackingRepository.findById(trackingId)
                .orElseThrow(() -> new RuntimeException("Tracking not found"));

        tracking.setTrackingStatus("DELIVERED");

        // Free up the agent
        DeliveryAgent agent = tracking.getAgent();
        agent.setAvailable(true);
        agentRepository.save(agent);

        // Update order status to DELIVERED
        Order order = tracking.getOrder();
        order.setStatus(OrderStatus.DELIVERED);
        order.setDeliveredAt(LocalDateTime.now());
        orderRepository.save(order);

        return trackingRepository.save(tracking);
    }

    /**
     * Admin: Add a delivery agent to the system
     */
    public DeliveryAgent addAgent(DeliveryAgent agent) {
        agent.setAvailable(true);
        return agentRepository.save(agent);
    }

    // Haversine formula: calculates distance between two GPS coords in km
    private double calculateDistance(double lat1, double lng1,
                                     double lat2, double lng2) {
        final int EARTH_RADIUS_KM = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c;
    }

    // Estimate delivery minutes: assumes 30 km/h city speed + 5 min buffer
    private long estimateMinutes(double distanceKm) {
        return Math.round((distanceKm / 30.0) * 60) + 5;
    }
}
```

---

### 📄 service/PaymentService.java
```java
package com.pizza.pizzaapp.service;

import com.pizza.pizzaapp.dto.PaymentRequest;
import com.pizza.pizzaapp.dto.PaymentResponse;
import com.pizza.pizzaapp.dto.PaymentVerifyRequest;
import com.pizza.pizzaapp.entity.Order;
import com.pizza.pizzaapp.entity.Payment;
import com.pizza.pizzaapp.repository.OrderRepository;
import com.pizza.pizzaapp.repository.PaymentRepository;
import com.razorpay.RazorpayClient;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private String keySecret;

    /**
     * STEP 1 — CREATE ORDER:
     * Creates a Razorpay order and returns the order ID to frontend.
     * Frontend uses this ID to open the Razorpay payment popup.
     */
    public PaymentResponse createPaymentOrder(PaymentRequest request) throws Exception {
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Initialize Razorpay client
        RazorpayClient client = new RazorpayClient(keyId, keySecret);

        // Razorpay expects amount in PAISE (1 INR = 100 paise)
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", (int)(order.getFinalAmount() * 100));
        orderRequest.put("currency", request.getCurrency() != null
                ? request.getCurrency() : "INR");
        orderRequest.put("receipt", "receipt_order_" + order.getId());

        com.razorpay.Order razorpayOrder = client.orders.create(orderRequest);
        String razorpayOrderId = razorpayOrder.get("id");

        // Save payment record with CREATED status
        Payment payment = Payment.builder()
                .order(order)
                .razorpayOrderId(razorpayOrderId)
                .amount(order.getFinalAmount())
                .currency(request.getCurrency() != null ? request.getCurrency() : "INR")
                .status("CREATED")
                .createdAt(LocalDateTime.now())
                .build();
        paymentRepository.save(payment);

        return new PaymentResponse(razorpayOrderId, order.getFinalAmount(),
                request.getCurrency() != null ? request.getCurrency() : "INR", keyId);
    }

    /**
     * STEP 2 — VERIFY PAYMENT:
     * After user pays, Razorpay sends back 3 values.
     * We verify the signature using HMAC-SHA256 to confirm it's authentic.
     */
    public String verifyPayment(PaymentVerifyRequest request) {
        // Signature must match: HMAC-SHA256(razorpayOrderId + "|" + paymentId, secret)
        String data = request.getRazorpayOrderId() + "|" + request.getRazorpayPaymentId();
        String generatedSignature = generateHmacSHA256(data, keySecret);

        Payment payment = paymentRepository.findByRazorpayOrderId(request.getRazorpayOrderId())
                .orElseThrow(() -> new RuntimeException("Payment record not found"));

        if (generatedSignature.equals(request.getRazorpaySignature())) {
            // Payment is genuine
            payment.setStatus("SUCCESS");
            payment.setRazorpayPaymentId(request.getRazorpayPaymentId());
            payment.setRazorpaySignature(request.getRazorpaySignature());
            payment.setUpdatedAt(LocalDateTime.now());
            paymentRepository.save(payment);
            return "Payment successful! Your order is confirmed.";
        } else {
            // Signature mismatch — possible fraud
            payment.setStatus("FAILED");
            payment.setUpdatedAt(LocalDateTime.now());
            paymentRepository.save(payment);
            throw new RuntimeException("Payment verification failed. Signature mismatch.");
        }
    }

    /**
     * Get payment details for an order
     */
    public Payment getPaymentByOrder(Long orderId) {
        return paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found for order: " + orderId));
    }

    // HMAC-SHA256 helper
    private String generateHmacSHA256(String data, String secret) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(
                    secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("HMAC generation error: " + e.getMessage());
        }
    }
}
```

---

### 📄 service/AnalyticsService.java
```java
package com.pizza.pizzaapp.service;

import com.pizza.pizzaapp.entity.Order;
import com.pizza.pizzaapp.entity.OrderStatus;
import com.pizza.pizzaapp.repository.OrderRepository;
import com.pizza.pizzaapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    /**
     * REVENUE ANALYTICS: Daily revenue for last N days.
     * Returns a map of date → revenue amount.
     */
    public Map<String, Double> getDailyRevenue(int days) {
        LocalDate from = LocalDate.now().minusDays(days);
        List<Order> orders = orderRepository.findByPlacedAtAfter(from.atStartOfDay());

        return orders.stream()
                .filter(o -> o.getStatus() != OrderStatus.CANCELLED)
                .collect(Collectors.groupingBy(
                        o -> o.getPlacedAt().toLocalDate().toString(),
                        Collectors.summingDouble(Order::getFinalAmount)
                ))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMa