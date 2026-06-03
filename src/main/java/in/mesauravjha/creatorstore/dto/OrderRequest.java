package in.mesauravjha.creatorstore.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    @NotBlank(message = "Name is required")
    private String customerName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    private String customerEmail;

    @Valid
    @NotEmpty(message = "Order must contain at least 1 item")
    private List<OrderItemRequest> items;

}
