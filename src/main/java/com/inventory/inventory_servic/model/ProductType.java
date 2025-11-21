package com.inventory.inventory_servic.model;

public enum ProductType {
    PERISHABLE,                //Comidad fresca, vence rapido
    NON_PERISHABLE,          //Arroz, azucar y etc
    PACKAGE,                // porductos empacados listo para la ventas
    BULK,                   // a GRANEL ( producto sueltos, por libra,como arroz por suelto y azucar y etc
    ANIMAL_FEED,             //Alimentos para mascotas
    MON_EDIBLE,              //nO COMESTIBLES
    BEVERAGE,                // LIQUIDOS
    FROZEN,                 // necesitan refrigeracion
    OTHER                   // Por si a caso
}
