package in.mesauravjha.creatorstore.services;

import in.mesauravjha.creatorstore.dto.OrderItemRequest;
import in.mesauravjha.creatorstore.dto.OrderRequest;
import in.mesauravjha.creatorstore.entities.Order;
import in.mesauravjha.creatorstore.entities.OrderItem;
import in.mesauravjha.creatorstore.entities.Product;
import in.mesauravjha.creatorstore.repositories.OrderRepository;
import in.mesauravjha.creatorstore.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    @Transactional
    public Order createOrder(OrderRequest orderRequest){
        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;
        Order order = new Order();
        order.setCustomerEmail(orderRequest.getCustomerName());
        order.setCustomerEmail(orderRequest.getCustomerEmail());
        order.setStatus("CONFIRMED");

        for(OrderItemRequest itemRequest: orderRequest.getItems()){
            Product product = productRepository.findById(itemRequest.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));

            if(product.getStockQuantity() < itemRequest.getQuantity()){
                throw new RuntimeException("Not enough stock for this product " + itemRequest.getProductId());
            }

            BigDecimal priceOfItem = product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            totalPrice = totalPrice.add(priceOfItem);

            product.setStockQuantity(product.getStockQuantity() - itemRequest.getQuantity());

            productRepository.save(product);

            OrderItem orderItem = OrderItem.builder().order(order).product(product).quantity(itemRequest.getQuantity()).priceAtPurchase(product.getPrice()).build();

            orderItems.add(orderItem);

        }

        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);
        return order;
    }
}
