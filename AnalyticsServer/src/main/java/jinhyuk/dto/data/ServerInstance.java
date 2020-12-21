package jinhyuk.dto.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ServerInstance {
    private String serverInstanceNo;
    private String serverName;
    private String privateIp;
    private CommonCode serverInstanceStatus;
}
