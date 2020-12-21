package jinhyuk.dto.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class LoadBalancedServerInstance {
    private ServerInstance serverInstance;
    private List<ServerHealthCheckStatus> serverHealthCheckStatusList;
}
