package wae.thesis.indiv.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import wae.thesis.indiv.api.model.ServiceDef;

import java.util.List;

/**
 * Created by Nguyen Tan Dat.
 */

@Data
@AllArgsConstructor
public class ServiceDto {
    private final ServiceDef serviceDef;
    private final List<ServiceDto> subServices;
}
