package jinhyuk.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class CreateNodeRequestDto {

    @NotNull
    private int serverCreateCount;

}
