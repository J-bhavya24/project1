# SmartPizza AI - Phase M2
# Repositories for Simple Mapped Entities

Package: com.wipro.pizzaapp.repository

---

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

import org.springframework.data.jpa.repository.JpaRepository;
import com.wipro.pizzaapp.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
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

# Checklist

1. Replace old repositories with these versions.
2. Save all files.
3. Run Maven Update Project.
4. Run Spring Boot App.
5. Fix any compilation errors before moving to services.

NEXT PHASE:
Mapped Services + Service Implementations
