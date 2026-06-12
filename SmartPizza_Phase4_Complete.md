# SmartPizza AI - Phase 4 Complete
## Service Interfaces

Package: com.wipro.pizzaapp.service

---

# UserService.java

```java
package com.wipro.pizzaapp.service;

import com.wipro.pizzaapp.entity.User;

public interface UserService {

    User register(User user);

    User getUser(Long id);

    User getByEmail(String email);
}
```

---

# AuthService.java

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

# PizzaService.java

```java
package com.wipro.pizzaapp.service;

import java.util.List;
import com.wipro.pizzaapp.entity.Pizza;

public interface PizzaService {

    Pizza savePizza(Pizza pizza);

    List<Pizza> getAllPizzas();

    Pizza getPizza(Long id);

    Pizza updatePizza(Long id, Pizza pizza);

    void deletePizza(Long id);
}
```

---

# CartService.java

```java
package com.wipro.pizzaapp.service;

import com.wipro.pizzaapp.entity.Cart;

public interface CartService {

    Cart addToCart(Long userId,
                   Long pizzaId,
                   Integer quantity);

    Cart getCart(Long userId);

    void clearCart(Long userId);
}
```

---

# OrderService.java

```java
package com.wipro.pizzaapp.service;

import java.util.List;
import com.wipro.pizzaapp.entity.Order;

public interface OrderService {

    Order placeOrder(Long userId);

    Order getOrder(Long orderId);

    List<Order> getOrdersByUser(Long userId);
}
```

---

# PaymentService.java

```java
package com.wipro.pizzaapp.service;

import com.wipro.pizzaapp.entity.Payment;

public interface PaymentService {

    Payment createPayment(Long orderId,
                          Double amount);

    Payment getPayment(Long id);
}
```

---

# DeliveryService.java

```java
package com.wipro.pizzaapp.service;

import com.wipro.pizzaapp.entity.Delivery;

public interface DeliveryService {

    Delivery trackOrder(Long orderId);

    Delivery updateStatus(Long orderId,
                          String status);
}
```

---

# RecommendationService.java

```java
package com.wipro.pizzaapp.service;

import java.util.List;
import com.wipro.pizzaapp.entity.Pizza;

public interface RecommendationService {

    List<Pizza> recommend(Long userId);
}
```

---

# AdminService.java

```java
package com.wipro.pizzaapp.service;

public interface AdminService {

    Long totalOrders();

    Double totalRevenue();
}
```

---

# Phase 4 Completion Checklist

1. Create service package
2. Create UserService
3. Create AuthService
4. Create PizzaService
5. Create CartService
6. Create OrderService
7. Create PaymentService
8. Create DeliveryService
9. Create RecommendationService
10. Create AdminService

NEXT PHASE:
Service Implementation Classes
(UserServiceImpl, AuthServiceImpl,
PizzaServiceImpl, CartServiceImpl,
OrderServiceImpl, PaymentServiceImpl,
DeliveryServiceImpl,
RecommendationServiceImpl,
AdminServiceImpl)
