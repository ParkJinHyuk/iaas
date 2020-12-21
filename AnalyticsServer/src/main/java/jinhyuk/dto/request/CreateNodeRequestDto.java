package jinhyuk.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateNodeRequestDto {

    private int serverCreateCount;

    public CreateNodeRequestDto(int serverCreateCount) {
        this.serverCreateCount = serverCreateCount;
    }

}
