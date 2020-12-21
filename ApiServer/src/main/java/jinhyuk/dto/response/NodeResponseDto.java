package jinhyuk.dto.response;

import jinhyuk.domain.clusters.Clusters;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
public class NodeResponseDto {

    private Long id;

    private String serverName;

    private String serverInstanceNo;

    private String privateIp;

    private String status;


    @Builder
    public NodeResponseDto(Long id, String serverName, String serverInstanceNo, String privateIp, String status) {
        this.id = id;
        this.serverName = serverName;
        this.serverInstanceNo = serverInstanceNo;
        this.privateIp = privateIp;
        this.status = status;
    }


}
