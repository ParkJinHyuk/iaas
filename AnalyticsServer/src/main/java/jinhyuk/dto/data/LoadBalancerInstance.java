package jinhyuk.dto.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class LoadBalancerInstance {

    private String loadBalancerInstanceNo;
    private String loadBalancerName;
    private List<LoadBalancedServerInstance> loadBalancedServerInstanceList;

}
