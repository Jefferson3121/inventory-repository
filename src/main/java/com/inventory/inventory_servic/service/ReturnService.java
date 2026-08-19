package com.inventory.inventory_servic.service;


import com.inventory.inventory_servic.dto.request.RequestReturnDTO;
import com.inventory.inventory_servic.dto.request.ResponseReturnDTO;

import java.util.List;

public interface ReturnService {
    ResponseReturnDTO createReturn(RequestReturnDTO requestReturnDTO);
    List<ResponseReturnDTO> getReturnsBySaleDetail(long saleDetailId);
}
