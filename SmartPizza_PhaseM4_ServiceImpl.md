# SmartPizza AI - Phase M4
# Service Implementation Classes (Mapped Entities)

Package: com.wipro.pizzaapp.serviceimpl

---

## UserServiceImpl.java

```java
package com.wipro.pizzaapp.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wipro.pizzaapp.entity.User;
import com.wipro.pizzaapp.repository.UserRepository;
import com.wipro.pizzaapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
```

---

## PizzaServiceImpl.java

```java
package com.wipro.pizzaapp.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.pizzaapp.entity.Pizza;
import com.wipro.pizzaapp.repository.PizzaRepository;
import com.wipro.pizzaapp.service.PizzaService;

@Service
public class PizzaServiceImpl implements PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Override
    public Pizza savePizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    @Override
    public Pizza getPizza(Long id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    @Override
    public Pizza updatePizza(Long id, Pizza pizza) {
        pizza.setId(id);
        return pizzaRepository.save(pizza);
    }

    @Override
    public void deletePizza(Long id) {
        pizzaRepository.deleteById(id);
    }
}
```

---

## CartServiceImpl.java

```java
package com.wipro.pizzaapp.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.pizzaapp.entity.Cart;
import com.wipro.pizzaapp.repository.CartRepository;
import com.wipro.pizzaapp.service.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
```

---

## OrderServiceImpl.java

```java
package com.wipro.pizzaapp.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.pizzaapp.entity.Order;
import com.wipro.pizzaapp.repository.OrderRepository;
import com.wipro.pizzaapp.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order placeOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void cancelOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
```

---

## PaymentServiceImpl.java

```java
package com.wipro.pizzaapp.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.pizzaapp.entity.Payment;
import com.wipro.pizzaapp.repository.PaymentRepository;
import com.wipro.pizzaapp.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPayment(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }
}
```

---

## DeliveryServiceImpl.java

```java
package com.wipro.pizzaapp.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.pizzaapp.entity.Delivery;
import com.wipro.pizzaapp.repository.DeliveryRepository;
import com.wipro.pizzaapp.service.DeliveryService;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public Delivery saveDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    @Override
    public Delivery getDelivery(Long id) {
        return deliveryRepository.findById(id).orElse(null);
    }
}
```

---

## RecommendationServiceImpl.java

```java
package com.wipro.pizzaapp.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.pizzaapp.entity.Pizza;
import com.wipro.pizzaapp.repository.PizzaRepository;
import com.wipro.pizzaapp.service.RecommendationService;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Override
    public List<Pizza> recommend(Long userId) {
        return pizzaRepository.findTop5ByOrderByPriceDesc();
    }
}
```

---

## AdminServiceImpl.java

```java
package com.wipro.pizzaapp.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.pizzaapp.repository.OrderRepository;
import com.wipro.pizzaapp.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Long totalOrders() {
        return orderRepository.count();
    }

    @Override
    public Double totalRevenue() {
        return 0.0;
    }
}
```

---

NOTE:
AuthServiceImpl will be generated together with controllers because it depends on JWT authentication flow.

NEXT PHASE:
Controllers + AuthServiceImpl + Swagger APIs
