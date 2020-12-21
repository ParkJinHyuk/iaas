package jinhyuk.dto.list;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class HealthCheckInstance {
    private String loadBalancerInstanceNo;
    private int age;

    public HealthCheckInstance(String loadBalancerInstanceNo, int age) {
        this.loadBalancerInstanceNo = loadBalancerInstanceNo;
        this.age = age;
    }
}
