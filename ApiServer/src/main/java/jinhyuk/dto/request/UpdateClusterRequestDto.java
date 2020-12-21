package jinhyuk.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Getter
public class UpdateClusterRequestDto {

    @Pattern(regexp = "all|exactly", message = "policy should be all or exactly")
    private String policy;
}
