package jinhyuk.dto.request;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import jinhyuk.domain.clusters.Clusters;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
public class CreateClusterRequestDto {

    @NotNull
    @Min(value = 2, message = "min serverCreateCount is 2")
    private int serverCreateCount;

    @NotBlank(message = "clusterName invalid")
    @Size(min = 1, max = 27, message = "clusterName length should be 1 ~ 27")
    private String clusterName;

    @NotBlank(message = "loadBalancerName invalid")
    @Size(min = 3, max = 30, message = "loadBalancerName length should be 3 ~ 30")
    private String loadBalancerName;

    @Pattern(regexp = "all|exactly", message = "policy should be all or exactly")
    private String policy;


    // Default 값 지정
    public CreateClusterRequestDto() {
        this.policy = "all";
    }


    public Clusters toEntity() {
        return Clusters.builder()
                .serverCreateStartNo(1)
                .serverCreateCount(0)
                .clusterName(clusterName)
                .policy(policy)
                .minNodeCnt(serverCreateCount)
                .status("CREATE")
                .build();
    }

    @Builder
    public CreateClusterRequestDto(int serverCreateCount, String clusterName, String loadBalancerName, String policy) {
        this.serverCreateCount = serverCreateCount;
        this.clusterName = clusterName;
        this.loadBalancerName = loadBalancerName;
        this.policy = policy;
    }
}
