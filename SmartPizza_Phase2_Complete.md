# SmartPizza AI - Phase 2 Complete
## Repositories + DTOs + Exception Handling

Package: com.wipro.pizzaapp

---

# REPOSITORIES

## UserRepository.java

```java
package com.wipro.pizzaapp.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wipro.pizzaapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
```

---

## PizzaRepository.java

```java
package com.wipro.pizzaapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wipro.pizzaapp.entity.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    List<Pizza> findByCategory(String category);

    List<Pizza> findTop5ByOrderByPriceDesc();
}
```

---

## CartRepository.java

```java
package com.wipro.pizzaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wipro.pizzaapp.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
```

---

## CartItemRepository.java

```java
package com.wipro.pizzaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wipro.pizzaapp.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
```

---

## OrderRepository.java

```java
package com.wipro.pizzaapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wipro.pizzaapp.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);
}
```

---

## OrderItemRepository.java

```java
package com.wipro.pizzaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wipro.pizzaapp.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
```

---

## PaymentRepository.java

```java
package com.wipro.pizzaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wipro.pizzaapp.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
```

---

## DeliveryRepository.java

```java
package com.wipro.pizzaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wipro.pizzaapp.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
```

---

# DTO PACKAGE

## RegisterRequest.java

```java
package com.wipro.pizzaapp.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
}
```

---

## LoginRequest.java

```java
package com.wipro.pizzaapp.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;
}
```

---

## AuthResponse.java

```java
package com.wipro.pizzaapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {

    private String token;
}
```

---

## PizzaDTO.java

```java
package com.wipro.pizzaapp.dto;

import lombok.Data;

@Data
public class PizzaDTO {

    private String name;
    private String category;
    private String description;
    private Double price;
}
```

---

## CartDTO.java

```java
package com.wipro.pizzaapp.dto;

import lombok.Data;

@Data
public class CartDTO {

    private Long pizzaId;

    private Integer quantity;
}
```

---

## OrderDTO.java

```java
package com.wipro.pizzaapp.dto;

import lombok.Data;

@Data
public class OrderDTO {

    private Long userId;
}
```

---

## PaymentDTO.java

```java
package com.wipro.pizzaapp.dto;

import lombok.Data;

@Data
public class PaymentDTO {

    private Long orderId;

    private Double amount;
}
```

---

## DeliveryDTO.java

```java
package com.wipro.pizzaapp.dto;

import lombok.Data;

@Data
public class DeliveryDTO {

    private Long orderId;

    private String status;
}
```

---

# EXCEPTION PACKAGE

## ResourceNotFoundException.java

```java
package com.wipro.pizzaapp.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```

---

## DuplicateResourceException.java

```java
package com.wipro.pizzaapp.exception;

public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }
}
```

---

## UnauthorizedException.java

```java
package com.wipro.pizzaapp.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }
}
```

---

## GlobalExceptionHandler.java

```java
package com.wipro.pizzaapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(
            ResourceNotFoundException ex) {

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(
            Exception ex) {

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

---

# Phase 2 Completion Checklist

1. Create repository package
2. Add all repository interfaces
3. Create dto package
4. Add all DTO classes
5. Create exception package
6. Add custom exceptions
7. Add GlobalExceptionHandler
8. Run project and verify no compilation errors

NEXT PHASE:
JWT Security + Authentication + Spring Security Configuration
