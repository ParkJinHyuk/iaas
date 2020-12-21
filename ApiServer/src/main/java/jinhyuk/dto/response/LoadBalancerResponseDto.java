package jinhyuk.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@NoArgsConstructor
@Getter
public class LoadBalancerResponseDto {

    private Long id;

    private String loadBalancerInstanceNo;

    private String loadBalancerName;


    @Builder
    public LoadBalancerResponseDto(Long id, String loadBalancerInstanceNo, String loadBalancerName) {
        this.id = id;
        this.loadBalancerInstanceNo = loadBalancerInstanceNo;
        this.loadBalancerName = loadBalancerName;
    }
}
