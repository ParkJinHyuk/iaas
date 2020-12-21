package jinhyuk.dto.message;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UpdateClusterRequestMessage {
    private Long clusterId;
    private String policy;

    public UpdateClusterRequestMessage(Long clusterId, String serverName, String policy) {
        this.clusterId = clusterId;
        this.policy = policy;
    }

}
