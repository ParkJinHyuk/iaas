package jinhyuk.dto.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class CreateLoadBalancerInstanceResponse {
    List<LoadBalancerInstance> loadBalancerInstanceList = new ArrayList<>();
}
