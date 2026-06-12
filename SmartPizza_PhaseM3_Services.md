# SmartPizza AI - Phase M3
# Service Interfaces for Mapped Entities

Package: com.wipro.pizzaapp.service

---

## UserService.java

```java
package com.wipro.pizzaapp.service;

import java.util.List;
import com.wipro.pizzaapp.entity.User;

public interface UserService {

    User register(User user);

    User getUser(Long id);

    List<User> getAllUsers();

    User getByEmail(String email);
}
```

---

## AuthService.java

```java
package com.wipro.pizzaapp.service;

import com.wipro.pizzaapp.dto.AuthResponse;
import com.wipro.pizzaapp.dto.LoginRequest;
import com.wipro.pizzaapp.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
```

---

## PizzaService.java

```java
package com.wipro.pizzaapp.service;

import java.util.List;
import com.wipro.pizzaapp.entity.Pizza;

public interface PizzaService {

    Pizza savePizza(Pizza pizza);

    Pizza getPizza(Long id);

    List<Pizza> getAllPizzas();

    Pizza updatePizza(Long id, Pizza pizza);

    void deletePizza(Long id);
}
```

---

## CartService.java

```java
package com.wipro.pizzaapp.service;

import com.wipro.pizzaapp.entity.Cart;

public interface CartService {

    Cart getCart(Long cartId);

    Cart saveCart(Cart cart);

    void deleteCart(Long cartId);
}
```

---

## OrderService.java

```java
package com.wipro.pizzaapp.service;

import java.util.List;
import com.wipro.pizzaapp.entity.Order;

public interface OrderService {

    Order placeOrder(Order order);

    Order getOrder(Long id);

    List<Order> getAllOrders();

    void cancelOrder(Long id);
}
```

---

## PaymentService.java

```java
package com.wipro.pizzaapp.service;

import com.wipro.pizzaapp.entity.Payment;

public interface PaymentService {

    Payment createPayment(Payment payment);

    Payment getPayment(Long id);
}
```

---

## DeliveryService.java

```java
package com.wipro.pizzaapp.service;

import com.wipro.pizzaapp.entity.Delivery;

public interface DeliveryService {

    Delivery saveDelivery(Delivery delivery);

    Delivery getDelivery(Long id);
}
```

---

## RecommendationService.java

```java
package com.wipro.pizzaapp.service;

import java.util.List;
import com.wipro.pizzaapp.entity.Pizza;

public interface RecommendationService {

    List<Pizza> recommend(Long userId);
}
```

---

## AdminService.java

```java
package com.wipro.pizzaapp.service;

public interface AdminService {

    Long totalOrders();

    Double totalRevenue();
}
```

---

# Checklist

1. Create all service interfaces.
2. Save files.
3. Verify no compile errors.
4. Run Spring Boot App.

NEXT PHASE:
Mapped Service Implementation Classes
