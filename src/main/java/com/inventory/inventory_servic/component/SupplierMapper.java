package com.inventory.inventory_servic.component;


import com.inventory.inventory_servic.domain.Address;
import com.inventory.inventory_servic.domain.Product;
import com.inventory.inventory_servic.dto.response.ResponseProductSummaryDTO;
import com.inventory.inventory_servic.domain.Supplier;
import com.inventory.inventory_servic.dto.request.RequestAddressDTO;
import com.inventory.inventory_servic.dto.request.RequestSupplierDTO;
import com.inventory.inventory_servic.dto.response.ResponseAddressDTO;
import com.inventory.inventory_servic.dto.response.ResponseSupplierDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = ProductMapper.class
)
public interface SupplierMapper {

//    ProductMapper getProductMapper();

    ResponseAddressDTO toResponseAddressDTO(Address address);
    Address mapAddress(RequestAddressDTO addressDTO);
    ResponseSupplierDTO toResponseSupplierDTO(Supplier supplier);
    List<ResponseSupplierDTO> toListResponseSupplierDTO(List<Supplier> suppliers);


    default Supplier toSupplier(RequestSupplierDTO requestSupplierDTO) {
        return Supplier.createSupplier(
                requestSupplierDTO.name(),
                requestSupplierDTO.contactName(),
                requestSupplierDTO.phone(),
                requestSupplierDTO.email(),
                mapAddress(requestSupplierDTO.address())
        );
    }





//    default ResponseSupplierDTO toResponseSupplierDTO(Supplier supplier) {
//        return new ResponseSupplierDTO(
//                supplier.getId(),
//                supplier.getName(),
//                supplier.getContactName(),
//                supplier.getPhone(),
//                supplier.getEmail(),
//                toResponseAddressDTO(supplier.getAddress()),
//                supplier.isActive(),
//                supplier.getProducts().stream().map(getProductMapper()::toResponseProductSummaryDTO).toList(),
//                supplier.getCreatedAt(),
//                supplier.getUpdatedAt()
//        );
//    }



}
