package jinhyuk.dto.message;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UpdateClusterRequestMessage {
    private Long clusterId;
    private String policy;

}
