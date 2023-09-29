package com.example.EatExpress.transformer;

import com.example.EatExpress.dto.requestDTO.MenuRequest;
import com.example.EatExpress.dto.responseDTO.MenuResponse;
import com.example.EatExpress.model.Menu;

public class MenuTransformer
{
    public static Menu MenuRequestToMenu(MenuRequest menuRequest)
    {
        return Menu.builder()
                .dishName(menuRequest.getDishName())
                .price(menuRequest.getPrice())
                .category(menuRequest.getCategory())
                .veg(menuRequest.isVeg())
                .available(menuRequest.isAvailable())
                .build();
    }

    public static MenuResponse MenuToMenuResponse(Menu menu)
    {
        return MenuResponse.builder()
                .dishName(menu.getDishName())
                .price(menu.getPrice())
                .veg(menu.isVeg())
                .category(menu.getCategory())
                .build();
    }
}
