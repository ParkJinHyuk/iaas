package jinhyuk.dto.list;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class HealthCheckInfo {

    private int age = 0;
    private boolean shouldCheck = true;

    public boolean getShouldCheck() {
        return shouldCheck;
    }

}
