package com.pheonix.internal_common.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ForecastPriceResponse {

    public Double price;
}
