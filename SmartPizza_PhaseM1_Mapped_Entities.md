# SmartPizza AI - Phase M1 Complete
# Fully Mapped JPA Entities

Package: com.wipro.pizzaapp.entity

## Role.java

```java
package com.wipro.pizzaapp.entity;

public enum Role {
    CUSTOMER,
    ADMIN,
    DELIVERY
}
```

---

## User.java

```java
package com.wipro.pizzaapp.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private Cart cart;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Order> orders;
}
```

---

## Pizza.java

```java
package com.wipro.pizzaapp.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String description;
    private Double price;
    private Boolean available;

    @OneToMany(mappedBy = "pizza")
    @JsonIgnore
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "pizza")
    @JsonIgnore
    private List<OrderItem> orderItems;
}
```

---

## Cart.java

```java
package com.wipro.pizzaapp.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    private Double totalPrice;

    @OneToMany(mappedBy = "cart",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    private List<CartItem> cartItems;
}
```

---

## CartItem.java

```java
package com.wipro.pizzaapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonBackReference
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    private Integer quantity;
}
```

---

## Order.java

```java
package com.wipro.pizzaapp.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    private Double totalAmount;

    private String status;

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderItem> orderItems;

    @OneToOne(mappedBy = "order",
            cascade = CascadeType.ALL)
    private Payment payment;

    @OneToOne(mappedBy = "order",
            cascade = CascadeType.ALL)
    private Delivery delivery;
}
```

---

## OrderItem.java

```java
package com.wipro.pizzaapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    private Integer quantity;

    private Double price;
}
```

---

## Payment.java

```java
package com.wipro.pizzaapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Double amount;

    private String paymentStatus;
}
```

---

## Delivery.java

```java
package com.wipro.pizzaapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String deliveryStatus;

    private Integer etaMinutes;
}
```
