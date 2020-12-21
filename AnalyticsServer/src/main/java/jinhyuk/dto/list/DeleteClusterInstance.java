package jinhyuk.dto.list;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class DeleteClusterInstance {

    private Long clusterId;
    private List<String> serverInstanceNoList;

    public DeleteClusterInstance(Long clusterId, List<String> serverInstanceNoList) {
        this.clusterId = clusterId;
        this.serverInstanceNoList = serverInstanceNoList;
    }

}
