package jinhyuk.dto.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class CreateServerInstancesResponse {
    private List<ServerInstance> serverInstanceList = new ArrayList<>();
}
