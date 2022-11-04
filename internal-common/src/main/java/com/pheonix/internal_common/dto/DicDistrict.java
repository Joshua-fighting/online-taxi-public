package com.pheonix.internal_common.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DicDistrict {

    private String address_code;

    private String address_name;

    private String parent_address_code;

    private Integer level;
}
