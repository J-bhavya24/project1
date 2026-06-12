# SmartPizza AI - Phase M5
# Controllers + AuthServiceImpl

Package:
- com.wipro.pizzaapp.controller
- com.wipro.pizzaapp.serviceimpl

--------------------------------------------------

## AuthServiceImpl.java

```java
package com.wipro.pizzaapp.serviceimpl;

import org.springframework.stereotype.Service;
import com.wipro.pizzaapp.dto.AuthResponse;
import com.wipro.pizzaapp.dto.LoginRequest;
import com.wipro.pizzaapp.dto.RegisterRequest;
import com.wipro.pizzaapp.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public AuthResponse register(RegisterRequest request) {
        return new AuthResponse("User Registered Successfully");
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return new AuthResponse("Login Successful");
    }
}
```

--------------------------------------------------

## AuthController.java

```java
package com.wipro.pizzaapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wipro.pizzaapp.dto.AuthResponse;
import com.wipro.pizzaapp.dto.LoginRequest;
import com.wipro.pizzaapp.dto.RegisterRequest;
import com.wipro.pizzaapp.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(
            @RequestBody RegisterRequest request) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}
```

--------------------------------------------------

## PizzaController.java

```java
package com.wipro.pizzaapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wipro.pizzaapp.entity.Pizza;
import com.wipro.pizzaapp.service.PizzaService;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<Pizza> getAllPizzas() {
        return pizzaService.getAllPizzas();
    }

    @GetMapping("/{id}")
    public Pizza getPizza(@PathVariable Long id) {
        return pizzaService.getPizza(id);
    }

    @PostMapping
    public Pizza savePizza(@RequestBody Pizza pizza) {
        return pizzaService.savePizza(pizza);
    }

    @PutMapping("/{id}")
    public Pizza updatePizza(
            @PathVariable Long id,
            @RequestBody Pizza pizza) {

        return pizzaService.updatePizza(id, pizza);
    }

    @DeleteMapping("/{id}")
    public void deletePizza(@PathVariable Long id) {
        pizzaService.deletePizza(id);
    }
}
```

--------------------------------------------------

## CartController.java

```java
package com.wipro.pizzaapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wipro.pizzaapp.entity.Cart;
import com.wipro.pizzaapp.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable Long id) {
        return cartService.getCart(id);
    }

    @PostMapping
    public Cart saveCart(@RequestBody Cart cart) {
        return cartService.saveCart(cart);
    }
}
```

--------------------------------------------------

## OrderController.java

```java
package com.wipro.pizzaapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wipro.pizzaapp.entity.Order;
import com.wipro.pizzaapp.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }
}
```

--------------------------------------------------

## PaymentController.java

```java
package com.wipro.pizzaapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wipro.pizzaapp.entity.Payment;
import com.wipro.pizzaapp.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Payment createPayment(
            @RequestBody Payment payment) {

        return paymentService.createPayment(payment);
    }
}
```

--------------------------------------------------

## DeliveryController.java

```java
package com.wipro.pizzaapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wipro.pizzaapp.entity.Delivery;
import com.wipro.pizzaapp.service.DeliveryService;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping
    public Delivery saveDelivery(
            @RequestBody Delivery delivery) {

        return deliveryService.saveDelivery(delivery);
    }
}
```

--------------------------------------------------

## RecommendationController.java

```java
package com.wipro.pizzaapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wipro.pizzaapp.entity.Pizza;
import com.wipro.pizzaapp.service.RecommendationService;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/{userId}")
    public List<Pizza> recommend(
            @PathVariable Long userId) {

        return recommendationService.recommend(userId);
    }
}
```

--------------------------------------------------

## AdminController.java

```java
package com.wipro.pizzaapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wipro.pizzaapp.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/stats")
    public Map<String,Object> stats() {

        Map<String,Object> map = new HashMap<>();

        map.put("totalOrders",
                adminService.totalOrders());

        map.put("totalRevenue",
                adminService.totalRevenue());

        return map;
    }
}
```

--------------------------------------------------

FINAL STEP

1. Create all controller classes.
2. Run Maven Update Project.
3. Run Project Clean.
4. Start Spring Boot App.
5. Open:

http://localhost:8080/swagger-ui/index.html

Swagger should now display APIs.
